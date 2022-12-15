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
import GivePlayerWins from '../views/GivePlayerWins.vue'

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
        title: "Home",
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        title: "Login",
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        title: "Logout",
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        title: "Register",
        requiresAuth: false
      }
    },

    {
      path: "/newTournament",
      name: "newTournament",
      component: CreateTournament,
      meta: {
        title: "Create A Tournament",
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId',
      name: "viewTournament",
      component: viewTournament,
      meta: {
        title: "Tournament",
        requiresAuth: true
      }
    },
    {
      path: "/terms",
      name: "termsOfService",
      component: termsOfService,
      meta: {
        title: "Terms of Service",
        requiresAuth: false
      }
    },

    {
      path: '/tournaments/:tournamentId/createMatches',
      name: 'createMatches',
      component: CreateMatches,
      meta: {
        title: "Set Your Matches",
        requiresAuth: true
      }
    },
    {
      path: "/tournaments/:tournamentId/admin",
      name: "admin",
      component: Admin,
      meta: {
        title: "Your Tournament",
        requiresAuth: true
      }
    },

    {
      path: "/tournaments/:tournamentId/sendMessage",
      name: "sendMessage",
      component: SendMessage,
      meta: {
        title: "Send A Ticket",
        requiresAuth: true
      }
    },

    {
      path: "/tournaments/:tournamentId/messages",
      name: "viewMessages",
      component: ViewMessages,
      meta: {
        title: "View Tickets",
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId/:messageId',
      name: "viewAMessage",
      component: ViewAMessage,
      meta: {
        title: "Ticket",
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId/matches/:roundNumber',
      name: 'createNewRounds',
      component: CreateNewRounds,
      meta: {
        title: "Set Rounds",
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId/nonAdmin',
      name: 'nonAdminView',
      component: TournamentNonAdminView,
      meta: {
        title: "Tournament",
        requiresAuth: false
      }
    },
    {
      path: '/:playerId/conversations',
      name: 'conversations',
      component: Conversation,
      meta: {
        title: "Your Conversations",
        requiresAuth: true
      }
    },
    {
      path: '/whispers/:playerId',
      name: 'whispers',
      component: Whisper,
      meta: {
        title: "Your Conversation",
        requiresAuth: true
      }
    },

    {
      path: '/tournaments/:tournamentId/admin/:playerId',
      name: "givePlayerWins",
      component: GivePlayerWins,
      meta: {
        title: "Record",
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

router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title} | Tourneo`;
  next();
});
export default router;
