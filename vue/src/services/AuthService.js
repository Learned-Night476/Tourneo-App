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
  },

  getAllPlayers() {
    return axios.get('/players')
  },

  getPlayerByUsername(username) {
    return axios.get(`/players/${username}`)
  },

  createMatch(match) {
    return axios.post('/matches', match)
  },

  getPlayerIdByUsername(username) {
    return axios.get(`/playerId/${username}`)
  },

  createTournamentUser(playerId, tournamentId) {
    return axios.post(`/${playerId}/${tournamentId}`)
  },

  getMatchByTournamentIdAndRound(tournamentId, round) {
    return axios.get(`tournaments/${tournamentId}/${round}`)
  },

  getTournamentUsers(tournamentId) {
    return axios.get(`tournamentUsers/${tournamentId}`)
  }

}
