interface Base {
  id: string;
  name: string;
}

interface Task extends Base {
  description: string;
}

interface Column extends Base {
  tasks: Task[];
}

interface Board extends Base {
  taskColumns: Column[];
}

interface Auth extends Base {
  token: string;
  isLoggedIn: Boolean;
  info: any;
}

interface TaskToEdit extends Task {
  columnParentId: string;
}
