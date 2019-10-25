import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import TestPage from './views/TestPage.vue'
import WorkbookPage from './views/WorkbookPage.vue'
Vue.use(Router)

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
      component: TestPage
    },
    {
      path: '/workbook',
      name: 'workbook',
      component: WorkbookPage
    }
  ]
})
