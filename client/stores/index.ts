import { defineStore } from "pinia";
import { v4 as uuidv4 } from "uuid";
import { useStorage } from "@vueuse/core";

export const useKanbanStore = defineStore("kanban", {
  state: () => ({
    boards: useStorage("board", [] as Board[] | undefined),
    columns: useStorage("column", [] as Column[] | undefined),
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
      const dataCreate = {
        name: taskInfos.name,
        taskColumnId: columnId,
        description: taskInfos.description,
      };
      try {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        const response = await fetch("http://127.0.0.1:8081/task/add", {
          method: "POST",
          headers,
          body: JSON.stringify(dataCreate),
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
        const response = await fetch("http://127.0.0.1:8081/board");
        this.boards = await response.json();
      } catch (error) {
        console.error(error);
      }
    },
    async fetchListTask(columnId: string) {
      try {
        const response = await fetch(
          "http://127.0.0.1:8081/task?taskColumnId=" + columnId
        );
        const data = await response.json();
        return data;
      } catch (error) {
        console.error(error);
      }
    },
    async createNewBoard(boardName: string) {
      const dataCreate = {
        name: boardName,
      };
      try {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        const response = await fetch("http://127.0.0.1:8081/board/add", {
          method: "POST",
          headers,
          body: JSON.stringify(dataCreate),
        });
        this.fetchListBoard();
      } catch (error) {
        console.error(error);
      }
    },
    async editTask(
      boardId: string,
      columnId: string,
      newColumnId: string,
      editedTask: Task,
      itemID: string
    ) {
      try {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        const response = await fetch(
          "http://127.0.0.1:8081/task/move/" + itemID,
          {
            method: "PUT",
            headers,
            body: JSON.stringify(editedTask),
          }
        );
        this.getBoardColumns(boardId);
      } catch (error) {
        console.error(error);
      }
    },
    async createNewColumn(boardId: string, columnName: string) {
      const dataCreate = {
        name: columnName,
        boardId,
      };
      try {
        const headers = new Headers();
        headers.append("Content-Type", "application/json");

        const response = await fetch("http://127.0.0.1:8081/column/add", {
          method: "POST",
          headers,
          body: JSON.stringify(dataCreate),
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
  },
});
