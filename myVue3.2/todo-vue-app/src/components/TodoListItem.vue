<!-- <template>
   <span class="pointer" 
          @click="upd(item.id)" 
          v-bind:class="item.completed ? 'todo-done' : ''">
          {{ item.todo }}{{ item.completed ? '(완료)' : '' }}</span>
    <span class="float-end badge bg-secondary pointer" @click="del(item.id)" v-on:del="del">삭제</span>
    <li>
        {{ item.id }}
    </li>
</template> -->
<template>
    <li class="list-group-item" 
        :class="{ 'list-group-item-success': todoItem.completed } "
        @click="$emit('toggle-completed', todoItem.id)" >
        <span class="pointer" :class="{ 'todo-done': todoItem.completed }">
            {{todoItem.todo}} {{ todoItem.completed ? "(완료)" : "" }}
        </span>
        <span class="float-end badge bg-secondary pointer"
            @click.stop="$emit('delete-todo', todoItem.id)">삭제</span>
    </li>
</template>
<script>
export default {
    name: "TodoListItem",
    data() {

    },
    props: ["todoItem"]
}

</script>