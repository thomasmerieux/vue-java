<template>

  <IconAndText class="ml-2" lib="fas" icon="plus-circle" label="Create a new list" v-on:clicked="createList"></IconAndText>

  <template v-if="data.length > 0">
    <div class="d-flex flex-wrap">
      <div v-for="item in data" :key="item.id"
           class="card m-2 p-5 d-flex align-items-center justify-content-center custom-list"
           style="width: 48%; height: 15rem; cursor: pointer"
           v-on:click="goToDetail(item.id)">
        <h1>{{ item.label }}</h1>
      </div>
    </div>

    <div v-for="item in detail" :key="item.id">
      <h3>{{ item.title }}</h3>
      <img height="300" v-bind:src="'https://image.tmdb.org/t/p/w500' + item.poster_path" alt="">
    </div>

  </template>
  <template v-else>
    <div class="w-100 h-100 p-5">
      <h1>No list available</h1>
    </div>
  </template>
</template>


<script>

import IconAndText from '../components/IconAndText.vue';

export default {
  name: 'List',
  components: {
    IconAndText
  },
  data() {
    return {
      data: [],
      detail: {}
    }
  },
  beforeMount() {
    this.getFavorites();
  },
  methods: {
    async getFavorites() {
      const res = await fetch(`http://localhost:8080/list`);
      this.data = await res.json();
    },
    async remove(id) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      const res = await fetch(`http://localhost:8080/watch-list/${id}`, requestOptions);
      const status = res.status;
      if (status === 200) {
        this.data.splice(this.data.findIndex(r => r.id === id), 1);
      }
    },
    async getDetail() {
      const res = await fetch(`http://localhost:8080/${this.$route.params.id}`);
      this.data = await res.json();
    },
    createList() {
      this.$router.push({
        path: `/list/create`
      });
    },
    goToDetail(id) {
      this.$router.push({
        path: `/list/${id}`
      });
    }
  }
}
</script>

<style>
.custom-list:hover {
  background-color: #EAEAEA;
}
</style>
