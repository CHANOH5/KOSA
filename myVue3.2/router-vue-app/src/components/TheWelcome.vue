<template>
  The Welcome
  <ul>
    <li>
      <!-- router-link속성으로 path설정 -->
      <router-link to="/">홈</router-link>
    </li>
    <li>
      <router-link to="/about">소개</router-link>
    </li>
    <li>
      <router-link to="/members">멤버</router-link>
    </li>
  </ul>
  </template>
  <script>
  export default{
    name: 'TheWelcome'
  }
  </script>