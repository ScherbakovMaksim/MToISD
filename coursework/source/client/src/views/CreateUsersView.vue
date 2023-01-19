<template>
  <div class="createUsers">
    <div>
        <h3 class="mx-auto" style="width: 100%">Добавление сотрудника / изменение информации о сотруднике</h3>
        
        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2"> </b-col>
          <b-col cols="4"> <label>Cотрудник (выберите, если необходимо изменить информацию)</label></b-col>
          <b-col cols="4"> <label>ИД сектора сотрудника</label></b-col>
          <b-col cols="4"> <label>ФИО сотрудника</label></b-col>
          <b-col cols="4"> <label>Телефон сотрудника</label></b-col>

          <b-col cols="2"> </b-col>
          </b-row>


        <b-row class="mx-auto" style="width: 100%">

          <b-col cols="2"> </b-col>
          <b-col cols="4"> 
            <b-form-select v-model="users"  @change="selectChange" class="mx-auto" style="width: 100%">        
            <template #first>
              <b-form-select-option :value=null>Добавить сотрудника</b-form-select-option>
              <b-form-select-option v-for="use in user" :key="use.id" :value="use">{{ use.name }}</b-form-select-option> 
            </template>
          </b-form-select>
           </b-col>

         

          <b-col cols="4"> <b-form-input v-model="sector_id"  @update="infChange"
            type='text' 
            placeholder="Введите ИД сектора сотрудника"> 
            required            
          </b-form-input></b-col>
          
          <b-col cols="2"> </b-col>
     


          <b-col cols="4"> <b-form-input v-model="name"  @update="infChange"
            type='text' 
            placeholder="Введите ФИО сотрудника"> 
            required            
          </b-form-input></b-col>
          <b-col cols="2"> </b-col>
     

          <b-col cols="4"> <b-form-input v-model="phone"  @update="infChange"
            type='text' 
            placeholder="Введите внутренний телефон сотрудника"> 
            required            
          </b-form-input></b-col>
          <b-col cols="2"> </b-col>
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >

        <b-button :disabled.sync="bdis" variant="outline-primary" @click="sendRequest">{{buttonText}}{{ name }} </b-button>
       
    </div>

    <div>
      <label> Результат запроса {{ error }}</label>
      <label> Выбрано {{id}}</label>
    </div>

</div>
</template>

<script>

import axios from 'axios'


export default{
name:'createUsers',

  data(){
    
    return{
      user:[
          { id: 1, sector_id: 1, name: 'a', phone: 456765},
          { id: 2, sector_id: 2, name: 'ab', phone: 455437},
          { id: 3, sector_id: 1, name: 'abc', phone: 345673},
          { id: 5, sector_id: 3, name: 'abcd', phone: 165448}],
      users:null,

      sector_id:'',
      name:'',
      phone:'',
      id:null,

      bdis:false,
      buttonText:'Добавить сотрудника: ',
      error:''      
    }
  },


  mounted(){    
    this.sendRequestUsers()
  },

  

  methods:{

    sendRequest(){ 
      this.bdis=true

      if (this.name != ''){
        if ((this.id !=null)&&(this.id!='')){
          axios.put('/server/users/updateUsers',{
            id:this.id,
            sector_id:this.sector_id,
            name:this.name,
            phone:this.phone
            /*
            headers: {
                    'Authorization': 'Bearer ' + this.$props.keycloak.token
                }
            */
          })
          .then(response => (this.error=response.data+' успешно', this.sendRequestUsers(),this.users=null, this.selectChange()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }else{
          axios.post('/server/users/createUsers', {
            id:this.id,
            sector_id:this.sector_id,
            name:this.name,
            phone:this.phone
          })
          .then(response => (this.error=response.data+' успешно', this.sendRequestUsers()))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
        }        
      }
      else{alert("Необходимо заполнить поля")}
    },


    sendRequestUsers(){
      axios
          .get('/server/users/loadUsers')
          .then(response => (this.users = response.data, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)});
    },


    infChange(){
      this.bdis=false
      
    },

    selectChange(){
      
      if ((this.users !=null)){ 
        this.buttonText='Обновить информацию о сотруднике: '
        this.sector_id=this.users.sector_id
        this.name=this.users.name
        this.phone=this.users.phone
      }
      else{
        this.buttonText='Добавить сотрудника: '
        this.id=null

      }
    }
  }

}
</script>
