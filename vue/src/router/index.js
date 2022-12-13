import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import CreateTournament from '../views/CreateTournament.vue'
import viewTournament from '../views/ViewTournament.vue'
import termsOfService from '../views/TermsOfService.vue'
import CreateMatches from '../views/CreateMatches.vue'
import Admin from '../views/Admin.vue'
import SendMessage from '../views/SendMessage.vue'
import ViewMessages from '../views/ViewMessages.vue'
import ViewAMessage from '../views/ViewAMessage.vue'
import CreateNewRounds from '../views/CreateNewRounds.vue'
import TournamentNonAdminView from '../views/TournamentNonAdminView.vue'
import Whisper from '../views/Whisper.vue'
import Conversation from  '../views/Conversation.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/newTournament",
      name: "newTournament",
      component: CreateTournament,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId',
      name: "viewTournament",
      component: viewTournament,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/terms",
      name: "termsOfService",
      component: termsOfService,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: '/tournaments/:tournamentId/createMatches',
      name: 'createMatches',
      component: CreateMatches,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/tournaments/:tournamentId/admin",
      name: "admin",
      component: Admin,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/tournaments/:tournamentId/sendMessage",
      name: "sendMessage",
      component: SendMessage,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: "/tournaments/:tournamentId/messages",
      name: "viewMessages",
      component: ViewMessages,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: '/tournaments/:tournamentId/:messageId',
      name: "viewAMessage",
      component: ViewAMessage,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: '/tournaments/:tournamentId/matches/:roundNumber',
      name: 'createNewRounds',
      component: CreateNewRounds,
      meta: {
        requiresAuth: false
      }
    },

    {
      path: '/tournaments/:tournamentId/nonAdmin',
      name: 'nonAdminView',
      component: TournamentNonAdminView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/:playerId/conversations',
      name: 'conversations',
      component: Conversation,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/:playerId/whispers/:playerId',
      name: 'whispers',
      component: Whisper,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
