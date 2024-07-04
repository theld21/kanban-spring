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
        let data = await this.handleFetchData(
          "api/task/add",
          {
            name: taskInfos.name,
            taskColumnId: columnId,
            description: taskInfos.description,
          },
          "POST"
        );
        if (!this.filterError(data)) return;

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
        let data = await this.handleFetchData("api/board");

        if (!this.filterError(data)) return;
        this.boards = data;
      } catch (error) {
        console.error(error);
      }
    },
    async fetchListTask(columnId: string) {
      try {
        let data = await this.handleFetchData(
          "api/task?taskColumnId=" + columnId
        );

        if (!this.filterError(data)) return;
        return data;
      } catch (error) {
        console.error(error);
      }
    },
    async createNewBoard(boardName: string) {
      try {
        let data = await this.handleFetchData(
          "api/board/add",
          {
            name: boardName,
          },
          "POST"
        );
        if (!this.filterError(data)) return;
        this.fetchListBoard();
      } catch (error) {
        console.error(error);
      }
    },
    async editTask(
      boardId: string,
      itemID: string,
      oldColumnId: Task,
      editedTask: Task
    ) {
      try {
        let data = await this.handleFetchData(
          "api/task/update/" + editedTask.id,
          editedTask,
          "POST"
        );
        if (!this.filterError(data)) return;

        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    async moveTask(
      boardId: string,
      fromColumnId: any,
      toColumnId: any,
      taskData: any,
      itemID: string
    ) {
      try {
        let data = await this.handleFetchData(
          "api/task/move/" + itemID,
          taskData,
          "POST"
        );
        if (!this.filterError(data)) return;

        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    async createNewColumn(boardId: string, columnName: string) {
      try {
        let data = await this.handleFetchData(
          "api/column/add",
          {
            name: columnName,
            boardId,
          },
          "POST"
        );

        if (!this.filterError(data)) return;
        await this.fetchListBoard();
        this.getBoardColumns(boardId);
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
    async deleteTask(boardId: string, taskId: string) {
      try {
        let data = await this.handleFetchData(
          "api/task/delete/" + taskId,
          null,
          "DELETE"
        );
        if (!this.filterError(data)) return;

        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    async login(email: string, password: string) {
      try {
        let data = await this.handleFetchData(
          "auth/login",
          {
            email,
            password,
          },
          "POST"
        );
        if (!this.filterError(data)) return;

        this.auth = {
          token: data.jwtToken,
          isLoggedIn: true,
          info: {
            email: data.username,
          },
        };
        await navigateTo("/");
      } catch (error) {
        console.error(error);
      }
    },
    async logout() {
      try {
        this.auth = {
          token: "",
          isLoggedIn: false,
          info: {},
        };
        await navigateTo("/login");
      } catch (error) {
        console.error(error);
      }
    },
    async register(email: string, password: string) {
      try {
        let data = await this.handleFetchData(
          "auth/register",
          {
            email,
            password,
          },
          "POST"
        );
        if (!this.filterError(data)) return;

        await navigateTo("/login");
      } catch (error) {
        console.error(error);
      }
    },
    filterError(res: any) {
      if (res.status === false) {
        alert(res.msg);
        return false;
      }
      return true;
    },
    async handleFetchData(
      path: string,
      data: any = null,
      method: string = "GET"
    ) {
      const headers = new Headers();
      headers.append("Content-Type", "application/json");
      headers.append("credentials", "include");
      headers.append("Authorization", `Bearer ${this.auth?.token}`);

      let payload = {};
      method != "GET" && data ? (payload.body = JSON.stringify(data)) : "";

      const response = await fetch("http://127.0.0.1:8081/" + path, {
        method,
        headers,
        ...payload,
      });

      let errCode = [401, 403];

      if (response.status && errCode.includes(response.status)) {
        this.auth = {};
        await navigateTo("/login");
      }

      try {
        return await response.json();
      } catch (error) {
        return {};
      }
    },
  },
});
