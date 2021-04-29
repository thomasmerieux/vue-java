<template>
  <template v-if="movies && movies.length > 0">

    <div v-for="(item, index) in movies" :key="item.id" class="card mb-4" :class="{ 'mt-3': index === 0 }"
         style="height: 14rem">
      <MovieListItem v-bind:item="item">
        <template v-slot:actions>
          <div class="pr-1" v-if="item.isWatchList">
            <IconAndText class="pr-4" lib="fas" icon="bookmark" label="Watch List" v-on:clicked="removeWatchList(item.id, index)"></IconAndText>
          </div>
          <div class="pr-1" v-if="!item.isWatchList">
            <IconAndText class="pr-4" lib="far" icon="bookmark" label="Watch List" v-on:clicked="addToWatchList(item.id, index)"></IconAndText>
          </div>
          <IconAndText class="pr-4" lib="fas" icon="list" label="Add to list" v-on:clicked="showModal(item)"></IconAndText>
          <IconAndText class="pr-4" lib="far" icon="times-circle" label="Remove from favorites" v-on:clicked="removeFromFavorite(item.id)"></IconAndText>
        </template>
      </MovieListItem>
    </div>
  </template>
  <template v-else-if="movies && movies.length === 0">
    <div class="w-100 h-100 p-5">
      <h1>Favorite list  is empty</h1>
    </div>
  </template>

  <Modal v-show="isModalVisible"
         @close="closeModal">
    <template v-slot:header>
      <template v-if="selectedMovie">
        {{ selectedMovie.title }}
      </template>
    </template>
    <template v-slot:body>
      <div class="mt-3"> Add this movie to one of your list :</div>
      <select name="" id="" v-model="selected">
        <option v-bind:value="item" v-for="item in lists" v-bind:key="item.id">
          {{ item.label }}
        </option>
      </select>

      <div class="d-flex align-items-center justify-content-center mt-5">
        <IconAndText class="pr-4" lib="fas" icon="plus-circle" label="Create a new list" v-on:clicked="createList()"></IconAndText>
      </div>
    </template>
    <template v-slot:footer>
      <button class="btn btn-primary" v-on:click="addToList(this.selected, this.selectedMovie)">
        Add to the list
      </button>
    </template>
  </Modal>

</template>


<script>
import Modal from '../components/Modal.vue';
import MovieListItem from '../components/MovieListItem.vue';
import IconAndText from '../components/IconAndText.vue';

export default {
  name: 'Favorite',
  components: {
    Modal,
    MovieListItem,
    IconAndText
  },
  data() {
    return {
      movies: null,
      isModalVisible: false,
      lists: [],
      selected: null,
      selectedMovie: null
    }
  },
  beforeMount() {
    setTimeout(this.$toast.clear, 3000);
    this.getFavorites();
    this.getLists();
  },
  methods: {
    showModal(item) {
      this.isModalVisible = true;
      this.selectedMovie = item;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    async getFavorites() {
      const res = await fetch(`http://localhost:8080/favorite`);
      const data = await res.json();
      const requests = [];
      for (const a of data) {
        requests.push(fetch(`http://localhost:8080/${a.movieId}`).then((r) => r.json()));
      }
      Promise.all(requests).then((r) => {
        this.movies = r;
      })
    },
    async getDetail(id) {
      const res = await fetch(`http://localhost:8080/${id}`);
      return await res.json();
    },
    async removeFromFavorite(id) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/favorite/${id}`, requestOptions).then(() => {
        this.getFavorites();
      });
    },
    async removeWatchList(id, index) {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/watch-list/${id}`, requestOptions).then(async () => {
        this.movies[index] = await this.getDetail(id);
      });
    },
    async addToWatchList(id, index) {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/watch-list/${id}`, requestOptions).then(async () => {
        this.movies[index] = await this.getDetail(id);
      });
    },
    async getLists() {
      const res = await fetch(`http://localhost:8080/list`)
      this.lists = await res.json();
      this.selected = this.lists[0];
    },
    async addToList(listId, movie) {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          movie_id: movie.id
        })
      };
      await fetch(`http://localhost:8080/list/${listId.id}`, requestOptions).then((res) => {
        if (res.status === 200) {
          this.$toast.success('Movie added to list');
          this.closeModal();
        } else if (res.status === 409) {
          this.$toast.error('This movie is already part of this list');
        }
      });
    },
    createList() {
      this.$router.push({
        path: `/list/create`, query: {
          movieId: this.selectedMovie.id
        }
      });
    }
  }
}
</script>
