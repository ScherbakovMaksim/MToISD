import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'listingUsers',
    component: () => import(/* webpackChunkName: "listing" */ '../views/ListingUsersView.vue'),
    
    meta: {
      rolesAllowed: 'readUsers' 
    } 
  },
  {
    path: '/createUsers',
    name: 'createUsers',

    component: () => import(/* webpackChunkName: "createUsers" */ '../views/CreateUsersView.vue'),
    
    meta: { 
      rolesAllowed: 'editUsers' 
    } 
  },  
]

const router = new VueRouter({
  routes
})

router .beforeEach((to, from, next) => { 
  
  const basePath = window .location.toString()
  if (! Vue.$keycloak.authenticated) {

    Vue.$keycloak.login({ redirectUri: basePath.slice(0, -1) + to.path })
  } else if ((Vue.$keycloak.hasResourceRole(to.meta.rolesAllowed))||(to ==null)) {
    Vue.$keycloak.updateToken()
      .then(() => {
        next()
      })
      .catch(err => { console
        .error(err) 
      }) 
  } else { 

    alert("У Вас нет права доступа на страницу " + to.name)
    next({ name: ('listingUsers') }) 
  } 
  
})

export default router
