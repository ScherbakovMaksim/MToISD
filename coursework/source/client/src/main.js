import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue} from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import VueAxios from 'vue-axios'

import '@/plugins/keycloak'
import { updateToken } from '@/plugins/keycloak-util'

/*
let initOptions = {
  url: 'http://127.0.0.1:8180/', realm: 'coursework', clientId: 'my-work', onLoad: 'login-required'
}

let keycloak = Keycloak(initOptions);

*/


Vue.use(BootstrapVue, axios, VueAxios)

Vue.config.productionTip = false

Vue.$keycloak.init({ onLoad: 'login-required' }).then((auth) => {
  if (!auth) {
    window.location.reload();
  } else {
    //Vue.$log.info("Authenticated");
    new Vue({
      router,
      el: '#app',
      render: h => h(App, )
    }).$mount('#app')

    
    window.onfocus = () => { 
      updateToken()
    }

  }
  /*
  localStorage.setItem("vue-token",keycloak.token);
  localStorage.setItem("vue-refresh-token",keycloak.refreshToken)
  */

//Token Refresh

/*
  setInterval(() => {
    keycloak.updateToken(70).then((refreshed) => {
      if (refreshed) {
        //Vue.$log.info('Token refreshed' + refreshed);
      } else {
        //Vue.$log.warn('Token not refreshed, valid for '
         // + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
      }
    }).catch(() => {
      //Vue.$log.error('Failed to refresh token');
    });
  }, 6000)
  */

}).catch(() => {
  //Vue.$log.error("Authenticated Failed");
});


/*
new Vue({
  router,
  render: h => h(App),

  
}).$mount('#app')
*/

