<template>
    <div v-if="loading" class="loading-container">
      <loading-animation></loading-animation>
    </div>
    <div v-else-if="error" class="error-container">
      <img src="../assets/AppIcon.webp" alt="App Icon" class="app-icon" />
      <div class="error-message">
        <div class="error-detail">{{ errorMessage }}</div>
      </div>
      <div class="status-icon">
      </div>
      <div class="retry-message">
        Reopen the app and check again
      </div>
      
    </div>
    <div v-else>
      <slot></slot>
    </div>
  </template>
  
  <script>
  import LoadingAnimation from './LoadingAnimation.vue';
  
  export default {
    components: {
      LoadingAnimation
    },
    data() {
      return {
        loading: true,
        error: false,
        errorMessage: '',
        errorType: ''
      };
    },
    mounted() {
      this.checkServer();
    },
    methods: {
      async checkServer() {
        try {
          const response = await fetch('https://carbelimserver.carbelim.io/');
          if (response.ok) {
            this.loading = false;
          } else {
            this.handleError('Server error encountered', 'server');
          }
        } catch (err) {
          this.handleError(
            err.message.includes('Failed to fetch')
              ? 'Internet connection issue'
              : 'An unexpected error occurred',
            'internet'
          );
        }
      },
      handleError(message, type) {
        this.loading = false;
        this.error = true;
        this.errorMessage = message;
        this.errorType = type;
      }
    }
  };
  </script>
  
  <style>
  body {
    margin: 0;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  }
  
  .loading-container,
  .error-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center;
  }
  
  .app-icon {
    width: 90px;
    height: 90px;
    margin-bottom: 1rem;
  }
  
  .error-message {
    font-weight: 500;
    font-size: 1.1rem;
    margin-bottom: 0.5rem;
    margin-top: -10px;
  }
  
  .retry-message {
    margin-top: -10px;
    font-weight: 500;
    font-size: 1rem;
    color: #333;
    margin-bottom: 0.5rem;
  }
  
  .error-detail {
    margin-top: -5px;
    font-size: 1.6rem;
    font-weight: 500;
    color: red;
  }
  </style>
  