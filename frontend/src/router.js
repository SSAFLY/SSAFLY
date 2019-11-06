import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import TestPage from './views/TestPage.vue'
import WorkbookPage from './views/WorkbookPage.vue'
import AdminPage from './views/AdminPage.vue'
import store from './vuex/store'

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
  if(store.state.user !== null || sessionStorage.getItem("user") !== null){
    return next();
  }
  alert("로그인을 먼저 해주세요")
  next('/')
}

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior (to, from, savedPosition) {
   // page scroll to top for all route navigations
   return { x: 0, y: 0 }
 },
  routes: [
		{
			path: '/',
			name: 'home',
			component: HomePage
    },
    {
      path: '/test',
      name: 'test',
      component: TestPage,
      beforeEnter: requireAuth()
    },
    {
      path: '/workbook',
      name: 'workbook',
      component: WorkbookPage,
      beforeEnter: requireAuth()
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminPage,
      beforeEnter: requireAuth()
    }
  ]
})
