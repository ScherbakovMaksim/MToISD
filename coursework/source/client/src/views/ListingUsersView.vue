<template>
  <div class="listingUsers">
    <div>
        <h3 class="mx-auto" style="width: 100%">Список сотрудников</h3>
          <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        
        </b-row>
        <h4>Фильтры</h4>
        <!-- Подписи полей -->
        <b-row class="mx-auto">
          <b-col cols="2"> <label>ИД сотрудника</label></b-col>
          <b-col cols="2"> <label>ИД сектора</label></b-col>
          <b-col cols="2"> <label>Имя сотрудника</label></b-col>
          <b-col cols="2"> <label>Телефон</label></b-col>

        </b-row>


        <b-row class="mx-auto" style="width: 100%">

          <b-col cols="2" > <b-form-input v-model="id"  @update="infChange"
            type='text' 
            placeholder=""
            >                        
          </b-form-input></b-col>

          <b-col cols="2" > <b-form-input v-model="sector_id"  @update="infChange"
            type='text' 
            placeholder=""
            >                        
          </b-form-input></b-col>
          
          <b-col cols="2" > <b-form-input v-model="name"  @update="infChange"
            type='text' 
            placeholder=""
            >                        
          </b-form-input></b-col>

          <b-col cols="2" > <b-form-input v-model="phone"  @update="infChange"
            type='text' 
            placeholder=""
            >                        
          </b-form-input></b-col>


        </b-row>

        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >

        <b-button :disabled.sync="bdis" variant="outline-primary"  @click="sendRequestAndChangeParam(null,null)">Вывести в соответствии с фильтром</b-button>


        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2">
            <b-button :disabled.sync="btnLeftDis" variant="outline-primary"  @click="sendRequest(user[0].id,'left')"  class="mx-auto" style="width: 100%" >Предыдущая страница</b-button>
          </b-col>

          <b-col cols="2">
            <b-button :disabled.sync="btnRightDis" variant="outline-primary"  @click="sendRequest(user[limit-1].id,'right')"  class="mx-auto" style="width: 100%" >Следующая страница</b-button>
          </b-col>

          <b-col cols="4"></b-col>

          <b-col cols="2">
            <b-button :disabled.sync="bupdatedis" variant="outline-primary"  @click="toUpdate()" class="mx-auto" style="width: 100%" v-if="accesCreateUsers">Редактировать</b-button>
          </b-col>

          <b-col cols="2">
            <b-button  variant="outline-primary"  @click="toCreate()" class="mx-auto" style="width: 100%" v-if="accesCreateUsers">Создать</b-button>
          </b-col>
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>
 
        <b-row class="mx-auto" style="width: 100%">
          
          <b-col cols="12">

            <b-table 
              id="usersstable"
              :items="user"
              :fields="fields"

              :select-mode="selectMode"
              selectable
              bordered
              @row-selected="onRowSelected"
              style="width: 100%"
            >
                           
            </b-table>

          </b-col>
          
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
         <b-col lq="2"> <label>  </label></b-col>
        </b-row>


        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="2">
            <b-button :disabled.sync="btnLeftDis" variant="outline-primary"  @click="sendRequest(user[0].id,'left')"  class="mx-auto" style="width: 100%" >Предыдущая страница</b-button>
          </b-col>

          <b-col cols="2">
            <b-button :disabled.sync="btnRightDis" variant="outline-primary"  @click="sendRequest(user[limit-1].id,'right')"  class="mx-auto" style="width: 100%" >Следующая страница</b-button>
          </b-col>
        </b-row>

        <b-row class="mx-auto" style="width: 100%">
          <b-col cols="4"> <label>  </label></b-col>
        </b-row >
       
    </div>
  </div>
</template>

<script>

  


import axios from 'axios'
import Vue from 'vue'


export default{
name:'createUsers',



  data(){
    
    return{
      users:[
          { id: 1, sector_id: 1, name: 'a', phone: 456765},
          { id: 2, sector_id: 2, name: 'ab', phone: 455437},
          { id: 3, sector_id: 1, name: 'abc', phone: 345673},
          { id: 5, sector_id: 3, name: 'abcd', phone: 165448}
      ], 
           
      //token:localStorage.getItem("vue-token"),
      //token:Vue.$keycloak.tokenParsed(),

      bdis:false,
      btnLeftDis:true,
      btnRightDis:false,
      bupdatedis:true,
      selectMode:'single',

      
      error:'',
      
      id:'',  
      sector_id:'',      
      name:'', 
      phone:'',


      fid:'',  
      fsector_id:'',      
      fname:'', 
      fphone:'',

      indexForUpdete:null,
      accesCreateUsers:Vue.$keycloak.hasResourceRole('editUsers'),
         
      limit:3, 

    }
  },


  mounted(){ 
    this.sendRequestUsers()
    this.sendRequest(null,null)
    console.log(`${this.accesCreateUsers}`)
  },

  

  methods:{
    

    sendRequest(anchor, direction){
      this.bdis=true
      
      

      axios
          .get('/server/users/loadUsersFilter',{
            params:{
              anchor: anchor,
              direction:direction,
              limit:this.limit,

              id:this.fid,
              sector_id:this.fsector_id,
              name:this.fname,
              phone:this.fphone,

            }  
          }
          )
          .then(response => (this.user = response.data.user, this.btnLeftDis=response.data.btnLeftDis,
                              this.btnRightDis=response.data.btnRightDis, this.error='Успешно'))
          .catch(e => {this.error=e, alert("Ошибка при выполнении запроса, проверьте подключение и повторите попытку: "+e)})
      

        
    },


    sendRequestAndChangeParam(){

      this.fid=this.id
      this.fsector_id=this.sector_id
      this.fname=this.name
      this.fphone=this.phone

      this.sendRequest(null,null)

    },



    infChange(){     //включение кнопки сохранения
      this.bdis=false
      
    },  

    onRowSelected(items){ //вызывается при выборе строки в таблице

      if (items.length>0){
        this.bupdatedis=false,       
       
       this.indexForUpdate=this.user.indexOf(items[0])
       
      }
      else{this.bupdatedis=true}
    },

    toUpdate(){
      //console.log(`Обновление накладной ${this.indexForUpdate}`)
      this.$router.push({ path: `/createUsers/${this.user[this.indexForUpdate].id}` })
    },

    toCreate(){
      this.$router.push({ path: `/createUsers/0` })
    }

  }

}
</script>


