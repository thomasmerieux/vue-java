<template>
  <Delete v-on:clicked="deleteList()" v-bind:label="'Delete list'"></Delete>

  <div class="w-100" style="position: relative">
    <input type="text" class="w-100" v-model="search" v-on:keyup="searchMovies()" placeholder="Search a movie">
    <template v-if="searchResults.length > 0">
      <div class="w-100 pt-2"
           style="height: 20rem; overflow-y: auto; position: absolute; z-index: 20; background-color: #FFFFFF; width: 100%; border: 1px solid rgba(0,0,0,.125)">
        <div v-for="(item) in searchResults" :key="item.id" class="mb-2 search-item"
             style="height: 6rem">
          <MovieListItem v-bind:item="item" v-bind:show-overview="false" class="mx-2 py-2" v-on:click="addToList(item)">
          </MovieListItem>
        </div>
      </div>
    </template>
  </div>

  <template v-if="movies !== undefined">
    <div v-for="(item, index) in movies" :key="item.id" class="card mb-4" :class="{ 'mt-3': index === 0 }"
         style="height: 14rem">
      <MovieListItem v-bind:item="item">
        <template v-slot:actions>
          <Delete v-on:clicked="remove(item.id)" v-bind:label="'Remove'"></Delete>
        </template>
      </MovieListItem>
    </div>
  </template>
</template>


<script>
import MovieListItem from "@/components/MovieListItem";
import Delete from "@/components/Delete";

export default {
  name: 'List Detail',
  components: {
    MovieListItem,
    Delete
  },
  data() {
    return {
      movies: null,
      search: '',
      searchResults: []
    }
  },
  beforeMount() {
    this.getListDetail();
  },
  methods: {
    async searchMovies() {
      const res = await fetch(`http://localhost:8080/search/${this.search}`);
      const data = await res.json();
      this.searchResults = data.results;
    },
    async addToList(movie) {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          movie_id: movie.id
        })
      };
      await fetch(`http://localhost:8080/list/${this.$route.params.id}`, requestOptions).then((res) => {
        if (res.status === 201) {
          this.$toast.success('Movie added to list');
          this.getListDetail();
          this.search = '';
          this.searchResults = [];
        } else if (res.status === 409) {
          this.$toast.error('This movie is already part of this list');
        }
      });
    },
    async getListDetail() {
      const res = await fetch(`http://localhost:8080/list/${this.$route.params.id}`);
      if (res.status ===  404) {
        this.$toast.error('List not found');
        await this.$router.push({
          path: `/list`
        });
      }
      const data = await res.json();
      const requests = [];
      for (const a of data) {
        requests.push(fetch(`http://localhost:8080/${a.movieId}`).then((r) => r.json()));
      }
      Promise.all(requests).then((r) => {
        this.movies = r;
      })
    },
    async remove(id) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      const res = await fetch(`http://localhost:8080/list/${this.$route.params.id}/movie/${id}`, requestOptions);
      const status = res.status;
      if (status === 204) {
        this.movies.splice(this.movies.findIndex(r => r.id === id), 1);
      }
    },
    async deleteList() {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/list/${this.$route.params.id}`, requestOptions).then(() => {
        this.$router.push({
          path: `/list`
        });
      });
    }
  }
}
</script>


<style>
.search-item:hover {
  background-color: #EAEAEA;
  cursor: pointer;
}
</style>
