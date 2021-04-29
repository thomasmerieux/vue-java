<template>

  <template v-if="popular !== undefined">
    <div style="min-height: 300px;
    height: calc(100vh / 2.5);
    max-height: 360px;" class="mt-3">
      <div :style="backDrop" class="w-100 h-100"
           style="background-repeat: no-repeat;background-size:cover;background-position: 50% 50%">
        <h1 class="pt-5" style="color: #FFFFFF">Welcome</h1>
        <h2 style="color: #FFFFFF">Explore millions of movies</h2>
        <form class="pt-5 w-100">
          <div class="form-check form-check-inline w-50">
            <input v-model="search" placeholder="Search for a movie" class="form-control" style="border-radius:0;"/>
            <button type="submit"
                    class="btn btn-primary"
                    v-on:click="this.searchMovie"
                    style="border-radius:0;"
                    :disabled="search.length === 0">
              Search
            </button>
          </div>
        </form>
      </div>
    </div>

    <div class="text-left mt-5">
      <h3>What's popular</h3>
      <div class="d-flex w-100 pt-2" style=" width: 30em;
    overflow-x: auto;
    white-space: nowrap;">
        <div class="pr-3" v-for="item in popular" :key="item.id" v-on:click="goTo(item.id)" style="cursor: pointer">
          <img height="250" v-bind:src="'https://image.tmdb.org/t/p/w500' + item.poster_path" alt="">
        </div>
      </div>
    </div>
  </template>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      popular: {},
      backDrop: null,
      search: ''
    }
  },
  methods: {
    searchMovie() {
      this.$router.push({
        path: `/search/${this.search}`
      });
    },
    async getPopularMovies() {
      const res = await fetch(`http://localhost:8080/popular`);
      this.popular = await res.json();
      this.getRandomPopularMovie(this.popular);
    },
    goTo(id) {
      this.$router.push({path: `/detail/${id}`});
    },
    getRandomPopularMovie(popularMovies) {
      const position = Math.floor(Math.random() * 20);
      const url = `url("https://image.tmdb.org/t/p/w1920_and_h600_multi_faces_filter(duotone,032541,01b4e4)${popularMovies[position].backdrop_path}")`;
      this.backDrop = {
        'background-image': url
      }
    }
  },
  beforeMount() {
    this.getPopularMovies();
  }
}
</script>
