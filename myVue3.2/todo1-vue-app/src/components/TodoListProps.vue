<template>
    <div class="row">
        <div class="col">
            <hr>
            <ul class="list-group">
                <TodoListItem v-for="todoItem in todoList" :key="todoItem.id" :todoItem="todoItem" @deleted="deletedTodo"
                    @toggleCompleted="toggleCompleted" />
            </ul>            
        </div>
    </div>
</template>
<script>
import TodoListItem from './TodoListItem.vue';
export default {
    name: 'todoList',
    components: { TodoListItem },
    data() {
        return {
            todoList: //목록과 바인드될 프로퍼티 
                [{ id: 1, todo: '내용1', completed: false },
                { id: 2, todo: '내용2', completed: true },
                { id: 3, todo: '내용3', completed: false },
                ]
                ,    
        }
    },    
    props: ["todo"],
    
    
    methods: {
        addTodo() {
            const ids = this.todoList.map(todo => { return todo.id })
            const maxId = ids.length == 0 ? 0 : Math.max(...ids)
            this.todoList.push({ id: maxId + 1, todo: this.todo, completed: false })       
            
        },
        deletedTodo(id) {
            let index = this.todoList.findIndex((item) => id === item.id);
            this.todoList.splice(index, 1);
        },
        toggleCompleted(id) {
            this.status = 'toggleCompleted'
            let index = this.todoList.findIndex((item) => id === item.id);
            this.todoList[index].completed = !this.todoList[index].completed;
        }
    },
    watch : { // 이 컴포넌트가 갖고있는 데이터의 변경을 감시하는 감시자 역할
              // 일반 데이터, todolist, props(부모로부터 전달 받은 데이터) 등을 감시 가능 함
              // 부모가 기존데이터와 다른 데이터를 전달했다면 watch가 감지함
		// todo(newTodo, oldTodo) {       // 방법 1 - 메서드 형태   
        //         this.addTodo(newTodo)
        // },
        todo: {                           // 방법 2 - 객체 형태
            handler: function (current) { // current == newTodo (변경된 값)
                console.log("todo데이터가 변경되었습니다");
                this.addTodo(current)
            }
        },
        todoList:{                         // 객체는 참조형이기 때문에 메모리가 변하지 않는 한 변화를 감지할 수 없다
                                           // 배열데이터는 참조형이다.
            deep: true, // 속성 내부 검사. -> deep 속성을 이용해서 true로 설정하면 객체의 내용변화까지 감지할 수 있음
            handler(current, old) {      
                console.log(current)   
            }
        }
    }
}
</script>
<style lang="">    
</style>