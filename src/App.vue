<template>
  <start-loading>
    <div>
      <loading-animation></loading-animation>
      <the-header></the-header>
      <main class="main-content">
        <router-view v-slot="{ Component }">
          <transition name="route" mode="out-in">
            <component :is="Component"></component>
          </transition>
        </router-view>
      </main>
    </div>
  </start-loading>
</template>

<script>
import TheHeader from './components/TheHeader.vue';
import LoadingAnimation from './components/LoadingAnimation.vue';
import StartLoading from './components/StartLoading.vue';

export default {
  components: {
    TheHeader,
    LoadingAnimation,
    StartLoading
  },
  mounted() {
    document.title = 'Carbelim Dashboard';
  }
};
</script>

<style>
.main-content {
  padding-top: 4.2rem; /* Adjust this value if your header height changes */
}

.route-enter-active, .route-leave-active {
  transition: all 0.3s ease;
}

.route-enter {
  opacity: 0;
  transform: translateX(100px);
}

.route-enter-to {
  opacity: 1;
  transform: translateX(0);
}

.route-leave {
  opacity: 1;
  transform: translateX(0);
}

.route-leave-to {
  opacity: 0;
  transform: translateX(-100px);
}
</style>
