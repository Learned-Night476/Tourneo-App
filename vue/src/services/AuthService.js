import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  getProfile(userId) {
    return axios.get(`/profile/${userId}`)
  },

  getAllUsers() {
    return axios.get('/users')
  },

  createATournament(tournament) {
    return axios.post(`/tournaments`, tournament);
  },

  getTournamentById(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}`)
  },

  getTournamentByUserId(userId) {
    return axios.get(`tournaments/user/${userId}`)
  }

}
