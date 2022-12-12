<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <p style="margin: 3px;" id="createId" >Account Creation</p>
      <h2 id="legendText">Become Legendary </h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-create"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-create"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-create"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div class="all-avatars">
      <label>
         <img src="../capstone-images/sword2.svg" id="img1" alt="button1" />
        <input type="radio" class="avatarButtons" name="avatar" checked id="button1" />
      </label>
         
         
      
        <label>
         <img src="../capstone-images/sword2.svg" id="img1" alt="button1" />
        <input type="radio" class="avatarButtons" name="avatar" checked id="button1" />
      </label>
      
      <label>
         <img src="../capstone-images/sword2.svg" id="img1" alt="button1" />
        <input type="radio" class="avatarButtons" name="avatar" checked id="button1" />
      </label>

      <label>
         <img src="../capstone-images/sword2.svg" id="img1" alt="button1" />
        <input type="radio" class="avatarButtons" name="avatar" checked id="button1" />
      </label>
      </div>
    
      <router-link class="needAnAccount" :to="{ name: 'login' }">Have an account?</router-link>
      <router-link  id="termsText" :to="{ name: 'termsOfService'}" target="_blank"> 
         <input id="checkbox" type="checkbox">
        Terms of Service And Privacy Policy
     </router-link>
      <button id="createButton" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account <a target="_blank" href="/terms"></a>
      </button>
      
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#legendText {
  animation: fadeInAnimation ease 6s;
  animation-fill-mode: forwards;
  color: red;
  }
  @keyframes fadeInAnimation {
  0% {
  opacity: 0;
  }
  100% {
  opacity: 1;
  }
}

.form-register {
  display: flex;
  flex-direction: column;
  color: white;
  margin-top: 10%;
  text-align: center;
  justify-content: space-evenly;
  align-items: center;
 }

#username {
  text-align: left;
}

.form-create {
  width: 40%;
  justify-content: space-evenly;
  padding: 10px;
  margin: 15px;
}

#createId {
  color: orange;
  font-size: 25px;
}

.avatarButtons {
  border: none;
  cursor: pointer;
  appearance: none;
  background-color: inherit;
}

img {
  width: 50px;
  height: 50px;
  
}

#img1 {
  width: 10vw;
  height: 10vw;
  padding: 2vw;
  border: solid 1px orange;
  border-radius: 50%;
}

input[type=radio] {
  display: none;
}

#img1:hover {
  opacity: 0.4;
  cursor: pointer;
}

#img1:active {
  opacity:0.4;
  cursor: pointer;
}

input[type=radio]:checked, #img1 {
  border: 5px solid orange;
}

label:focus-within {
  outline: 1px solid orange;
}

#createButton {
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 40px;
  width: 200px;
  border-radius: 4px;

}

#createButton:hover {
  background-color: rgba(140, 0, 255, 0.75);
}

.form-create {
  background-color:rgba(128, 128, 128, 0.664);
  color: orange;
}

.form-create::placeholder {
  color:white;
  font-size: 15px;
}

#termsText {
  padding: 10px;
  text-decoration: none;
}

#termsText:hover {
  color:orangered;
}

input[type=checkbox] {
          accent-color: orangered;
        }



 /* .avatars:hover {
background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/capstone-images/sword2.svg");
    background-repeat: no-repeat;
    background-position: center;

}  */
/* 
[type=radio] {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}

[type=radio] + img {
  cursor: pointer;
}

[type=radio]:checked + img {
  outline: 2px solid #f00;
} */
/* .radio {
   background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/capstone-images/sword2.svg");
    background-repeat: no-repeat;
    background-position: center;
    background-color: gray;
    text-align:left;
    padding: 10px;
    height: 200px;
    font-size: x-large;
    text-decoration: none;
}   */
/* .img {
  display: flex;
  align-items: end;
}

img {
  border: 1px solid black;
}

.radio {
  position: relative;
  height: 50px;
}

input[type="radio"] {
  position: absolute;
  top: calc(50% - 4px);
  right: 0;
  margin: 0;
} */





</style>
