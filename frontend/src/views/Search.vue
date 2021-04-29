<template>
  <div class="form-check form-check-inline w-50 py-3">
              <input v-model="search" placeholder="Search for a movie" class="form-control" style="border-radius:0;"/>
              <button type="submit"
                      class="btn btn-primary"
                      v-on:click="this.searchMovies"
                      style="border-radius:0;"
                      :disabled="search.length === 0">
                Search
              </button>
            </div>
  <template v-if="searchResults">
    <div v-for="item in searchResults" :key="item.id" v-on:click="goTo(item.id)" style="height: 14rem; cursor: pointer"
         class="card mb-4">
      <MovieListItem v-bind:item="item"></MovieListItem>
    </div>
  </template>
</template>


<script>
import MovieListItem from "@/components/MovieListItem";

export default {
  name: 'Search',
  components: {
    MovieListItem
  },
  data() {
    return {
      searchResults: null,
      search: this.$route.params.query
    }
  },
  methods: {
    async searchMovies() {
      const res = await fetch(`http://localhost:8080/search/${this.search}`);
      const data = await res.json();
      this.searchResults = data.results.filter(i => {
        return i.poster_path !== null;
      });
    },
    goTo(id) {
      this.$router.push({path: `/detail/${id}`});
    }
  },
  beforeMount() {
    this.searchMovies();
  }
}
</script>
