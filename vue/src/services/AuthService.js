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

  createTournamentUser(playerId, tournamentId, tournamentUser) {
    return axios.post(`/${playerId}/${tournamentId}`, tournamentUser)
  },

  getMatchByTournamentIdAndRound(tournamentId, round) {
    return axios.get(`tournaments/${tournamentId}/${round}`)
  },

  getTournamentUsers(tournamentId) {
    return axios.get(`tournamentUsers/${tournamentId}`)
  },

  sendMessage(message) {
    return axios.post('/messages', message)
  },

  getMessagesByTournamentId(tournamentId) {
    return axios.get(`/tournaments/${tournamentId}/messages`)
  },

  getMessageByMessageId(messageId) {
    return axios.get(`/messages/${messageId}`)
  },

  getTournamentsParticipated(userId) {
    return axios.get(`/tournaments/participated/${userId}`)
  },

  getPlayersByTournamentId(tournamentId) {
    return axios.get(`/players/tournament/${tournamentId}`)
  },

  getTournamentTypeById(id){
    return axios.get(`tournamentType/tournaments/${id}`)
  },

  getWhisperByPlayerId(playerId) {
    return axios.get(`/whispers/${playerId}`)
  },

  givePlayerAWin(playerId, wins){
    return axios.put(`/players/${playerId}/${wins}`)
  },

  getPlayerByPlayerId(playerId){
    return axios.get(`/players/player/${playerId}`)
  },

  givePlayerALoss(playerId, losses){
    return axios.put(`/players/playerLosses/${playerId}/${losses}`)
  },

  sendWhisper(whisper) {
    return axios.post(`/whispers`, whisper)
  },

  markTournamentCompleted(tournamentId){
    return axios.put(`/tournamentStatus/${tournamentId}`)
  },

  getMatch(tournamentId, playerId) {
    return axios.get(`/tournamentMessage/${tournamentId}/${playerId}`)
  },

  updateMatchWinner(tournamentId, winner) {
    return axios.put(`/tournaments/${tournamentId}/matches/${winner}`)
  },

  getTournamentUserById(tournamentId, playerId) {
    return axios.get(`/${tournamentId}/tournamentUsers/${playerId}`)
  },

  getActiveTournamentUsers(tournamentId) {
    return axios.get(`tournamentUsers/active/${tournamentId}`)
  },

  setTournamentWinner(tournamentId, username) {
    return axios.put(`tournamentWinner/${tournamentId}/${username}`)
  }

}
