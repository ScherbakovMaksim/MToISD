<template>
  <div id="app">
    <b-row class="mx-auto" style="width: 100%">

      <b-col cols="12">
        <h2>КУРСОВАЯ РАБОТА по дисциплине СОВРЕМЕННЫЕ ТЕХНОЛОГИИ ПРОМЫШЛЕННОЙ РАЗРАБОТКИ ПРОГРАММНОГО ОБЕСПЕЧЕНИЯ на тему: проектирование системы структуризации и хранения данных персонала предприятия</h2>
        <h3>Исполнитель, студент ИВМ-22 Щербаков М.И.</h3>
      </b-col>

    </b-row>

    <b-row class="mx-auto" style="width: 100%">
      <b-col cols="1"></b-col>
      <b-col cols="10">
        <nav>   

          <router-link to="/" v-if="accesListingUsers">Список сотрудников</router-link> <label v-if="accesListingUsers"> | </label> 
          <router-link to="/createUsers" v-if="accesCreateUsers">Добавление сотрудника</router-link> <label v-if="accesCreateUsers"> | </label>          
        </nav>
         
      </b-col>
      <b-col cols="1">
        <b-button  variant="outline-primary"  @click="logOut()">Выход</b-button>
      </b-col>

    </b-row>
    <router-view/>
    
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>


<script>

import Vue from 'vue'
import axios from 'axios'
import { updateToken } from '@/plugins/keycloak-util'

//const AUTHORIZATION_HEADER = 'Authorization'

export default Vue.extend({
  name: 'App',

  data(){
    return{

      accesListingUsers:Vue.$keycloak.hasResourceRole('readUsers'),
      accesCreateUsers:Vue.$keycloak.hasResourceRole('editUsers'),

    }
  },

  created: function () {
    axios.interceptors.request.use(async config => {
      const token =  await updateToken() 
      config.headers.Authorization=`Bearer ${token}`      
      return config
    })
    
    axios.interceptors.response.use( (response) => {
      return response
    }, error => {
      return new Promise((resolve, reject) => { 
        //alert("Произошла ошибка при выполнении запроса " +error )
        reject(error)
      })
    })
  },
  


  

  methods:{
    logOut(){
      const basePath = window .location.toString()
      Vue.$keycloak.logout({ redirectUri: basePath.slice(0, -1) })
    },

    recomputeAcces(){
      this.accesListingUsers=Vue.$keycloak.hasResourceRole('readUsers'),
      this.accesCreateUsers=Vue.$keycloak.hasResourceRole('editUsers')

    }

  },

  watch: {
    $route() {
      updateToken(),
      this.recomputeAcces()
    }
  },

})

</script>
