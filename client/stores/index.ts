import { defineStore } from "pinia";
import { useStorage } from "@vueuse/core";

export const useKanbanStore = defineStore("kanban", {
  state: () => ({
    boards: useStorage("board", [] as Board[] | undefined),
    columns: useStorage("column", [] as Column[] | undefined),
    auth: useStorage("auth", [] as Auth[] | undefined),
  }),
  actions: {
    async getBoardColumns(
      boardId: string,
      fetchTask = true
    ): Column[] | undefined {
      const findBoard = this.boards?.find((board) => board.id == boardId);
      const self = this;

      this.columns = findBoard?.taskColumns;

      if (!this.columns || !fetchTask) {
        return;
      }

      for (const column of this.columns) {
        column.tasks = await self.fetchListTask(column.id);
      }
    },
    async getColumnTasks(boardId: string, columnId: string) {
      const column = this.getBoardColumns(boardId)?.find(
        (column) => column.id === columnId
      );
      if (!column) {
        return [];
      }
      let result = await this.fetchListTask(column?.id);

      return result;
    },
    async addTaskToColumn(
      boardId: string,
      columnId: string,
      taskInfos: Omit<Task, "id">
    ) {
      try {
        await this.handleFetchData("http://127.0.0.1:8081/api/task/add", {
          name: taskInfos.name,
          taskColumnId: columnId,
          description: taskInfos.description,
        });
        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    removeTaskFromColumn(boardId: string, columnId: string, editedTask: Task) {
      const boardTasks = this.getColumnTasks(boardId, columnId);
      const filteredTasks = boardTasks!.filter(
        (task) => task.id !== editedTask.id
      );
      //Removing task from original column
      this.boards!.find((board) => board.id === boardId)!.columns.find(
        (column) => column.id === columnId
      )!.tasks = filteredTasks;
    },
    async fetchListBoard() {
      try {
        const response = await this.handleFetchData("http://127.0.0.1:8081/api/board");
        this.boards = await response.json();
      } catch (error) {
        console.error(error);
      }
    },
    async fetchListTask(columnId: string) {
      try {
        const data = await this.handleFetchData('api/task?taskColumnId=' + columnId);
        return data;
      } catch (error) {
        console.error(error);
      }
    },
    async createNewBoard(boardName: string) {
      try {
        await this.handleFetchData('api/board/add', {
          name: boardName,
        });
        this.fetchListBoard();
      } catch (error) {
        console.error(error);
      }
    },
    async editTask(
      boardId: string,
      editedTask: Task,
      itemID: string
    ) {
      try {
        await this.handleFetchData('api/task/move/' + itemID, editedTask);
        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    async createNewColumn(boardId: string, columnName: string) {
      try {
        this.handleFetchData('api/column/add', {
          name: columnName,
          boardId,
        });
        this.fetchListBoard();
      } catch (error) {
        console.error(error);
      }
    },
    editColumnName(boardId: string, columnId: string, columnName: string) {
      this.boards!.find((board) => board.id === boardId)!.columns.find(
        (column) => column.id === columnId
      )!.name = columnName;
    },
    editBoard(boardId: string, newBoardName: string, newColumnsName: Column[]) {
      const findBoard = this.boards!.find((board) => board.id === boardId)!;
      findBoard.name = newBoardName;
      findBoard.columns = newColumnsName;
    },
    deleteBoard(boardId: string) {
      this.boards!.splice(
        this.boards!.findIndex((board) => board.id === boardId),
        1
      );
    },
    async login(email: string, password: string) {
      try {
        let data = await this.handleFetchData('auth/login', {
          email,
          password,
        });
        localStorage.setItem('token', data.token);
        this.auth = {
          token: '',
          isLoggedIn: true,
          info: {},
        };
      } catch (error) {
        console.error(error);
      }
    },
    async handleFetchData(path: string, data: any = null){
      const headers = new Headers();
      headers.append("Content-Type", "application/json");
      headers.append("Authorization", `Bearer ${this.auth?.token}`); 

      const response = await fetch("http://127.0.0.1:8081/" + path, {
        method: "POST",
        headers,
        body: JSON.stringify(data),
      });
      return response.json();
    }
  },
});
