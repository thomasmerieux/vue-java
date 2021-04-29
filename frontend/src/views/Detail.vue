<template>
  <div class="w-100 h-100" v-if="data !== undefined && data !== null">
    <div class="card w-100 h-100 mt-4">
      <div class="d-flex"
           style="height: 65%; width: 100%;text-align: left">
        <div class="h-100 p-4">
          <div class="align-middle" style="height: 100%">
            <img style="width: 100%" v-bind:src="'https://image.tmdb.org/t/p/w500' + data.poster_path" alt="">
          </div>
        </div>
        <div class="h-100 w-100 p-4">
          <h1 class="mb-0">
            {{ data.title }} <span style="font-weight: lighter;font-size: 2.2rem;">
              ({{ getReleaseYear(data.release_date) }})
            </span>
          </h1>
          <div>
            {{ data.genres.map(r => r.name).join(', ') }} - {{ data.runtime }}mn
          </div>
          <div class="mt-5"><h4>{{ data.tagline }}</h4></div>
          <div class="mt-3">
            <h5>Synopsis</h5>
            <div>{{ data.overview }}</div>
          </div>
          <div class="d-flex mt-5">
            <div class="d-flex align-items-center justify-content-center mr-3" style="
  width: 50px; height: 50px;
  background-color: #f1f5f1;
  border-radius: 50px;
  border: 1px solid;
  cursor: pointer;
"
                 v-on:click="data.isFavorite ? removeFromFavorite() : addToFavorite()">
              <template v-if="data.isFavorite">
                <font-awesome-icon :icon="['fas', 'heart']" size="lg"/>
              </template>
              <template v-else>
                <font-awesome-icon :icon="['far', 'heart']" size="lg"/>
              </template>
            </div>
            <div class="d-flex align-items-center justify-content-center"
                 style="width: 50px; height: 50px;background-color: #f1f5f1;border-radius: 50px;border: 1px solid;cursor: pointer;"
            v-on:click="data.isWatchList ? removeWatchList() : addToWatchList()">
              <template v-if="data.isWatchList">
                <font-awesome-icon :icon="['fas', 'bookmark']" size="lg"/>
              </template>
              <template v-else>
                <font-awesome-icon :icon="['far', 'bookmark']" size="lg"/>
              </template>
            </div>
          </div>

        </div>
      </div>
    </div>

    <h1 class="my-3 text-left">Cast</h1>
    <div class="d-flex w-100 pt-2 mb-5" style=" width: 30em;
    overflow-x: auto;
    white-space: nowrap;">
      <div class="pr-3" v-for="item in data.credits.cast.filter(r => r.profile_path !== null)" :key="item.id"
           style="position: relative">
        <CastItem v-bind:item="item"></CastItem>
      </div>
    </div>
  </div>
</template>


<script>
import CastItem from "@/components/CastItem";


export default {
  name: 'Detail',
  components: {
    CastItem
  },
  data() {
    return {
      data: null
    }
  },
  beforeMount() {
    this.getDetail();
  },
  methods: {
    async getDetail() {
      const res = await fetch(`http://localhost:8080/${this.$route.params.id}`);
      this.data = await res.json();
    },
    getReleaseYear(releaseDate) {
      const a = new Date(releaseDate);
      return a.getFullYear();
    },
    async addToFavorite() {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/favorite/${this.$route.params.id}`, requestOptions).then(() => {
        this.getDetail();
      });
    },

    async removeFromFavorite() {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/favorite/${this.$route.params.id}`, requestOptions).then(() => {
        this.getDetail();
      });
    },
    async addToWatchList() {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/watch-list/${this.$route.params.id}`, requestOptions).then(() => {
        this.getDetail();
      });
    },
    async removeWatchList() {
      const requestOptions = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"}
      };
      await fetch(`http://localhost:8080/watch-list/${this.$route.params.id}`, requestOptions).then(() => {
        this.getDetail();
      });
    }
  },

}
</script>
