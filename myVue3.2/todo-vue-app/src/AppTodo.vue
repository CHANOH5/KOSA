<template>
    <div class="card card-body bg-light">
        
			<div class="title">:: Todolist App</div>           
            
            <TodoInput v-model:todo="todo" v-on:add="add" />
            <TodoList :todolist="todolist" v-on:delete-todo="deletetodo" v-on:toggle-completed="togglecompleted" />
        
        </div>
</template>
<script>
import TodoInput from "./components/TodoInput.vue"
import TodoList from "./components/TodoList.vue"

export default {
    name: "AppTodo",
    components: { TodoInput, TodoList },
    data() {
        return {
            todo: '', //텍스트박스에 사용자가 입력한 내용과 바인드될 프로퍼티
            todolist: [{id:1, todo:'aaa', completed:false},
                        {id:1, todo:'aaa', completed:false},
            ],
        } // return
    }, // data
    methods: {
        add() {
            const ids = this.todolist.map(todo => todo.id);
            const maxId = ids.length === 0 ? 0 : Math.max(...ids);
            this.todolist.push({ id: maxId + 1, todo: this.todo, completed: false });

            this.todo= '';
        },
        deletetodo(id) {
            const index = this.todolist.findIndex(todo => todo.id === id);
            this.todolist.splice(index, 1);
        },
        togglecompleted(id) {
            const index = this.todolist.findIndex(todo => todo.id === id);
            this.todolist[index].completed = !this.todolist[index].completed;
        },
    },
} // export

</script>

<style scoped>
@import "https://unpkg.com/bootstrap@5.2.3/dist/css/bootstrap.min.css";

body {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
}

.title {
    text-align: center;
    font-weight: bold;
    font-size: 20pt;
}

.todo-done {
    text-decoration: line-through;
}

.container {
    padding: 10px 10px 10px 10px;
}

.card-borderless {
    border: 0;
    box-shadow: none;
}

.pointer {
    cursor: pointer;
}

</style>
