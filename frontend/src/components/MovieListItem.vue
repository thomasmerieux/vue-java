<template>
  <div class="text-left w-100 h-100">
    <div class="d-flex w-100 h-100">
      <div class="pr-3 h-100">
        <template v-if="item.poster_path !== undefined">
          <img class="h-100" v-bind:src="'https://image.tmdb.org/t/p/w500' + item.poster_path" alt="">
        </template>
      </div>
      <div class="d-flex flex-column pt-3">
        <h3>{{ item.title }}</h3>
        <div class="pt-2 h-100" v-if="showOverview">{{ item.overview }}</div>
        <div class="d-flex align-items-center pb-2">
          <slot name="actions">
          </slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Movie List Item',
  props: {
    item: {
      title: String,
      poster_path: String,
      overview: String,
      required: true
    },
    showOverview: {
      type: Boolean,
      default: true
    }
  }
}
</script>
