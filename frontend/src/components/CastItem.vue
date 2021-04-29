<template>
  <div style="width: 10rem;border: solid 1px rgba(78,53,53,0.21);">
    <template v-if="item.profile_path !== null">
      <img style="max-width:100%;height: auto;" v-bind:src="'https://image.tmdb.org/t/p/w500' + item.profile_path"
           alt="">
    </template>
    <div class="px-1 pt-1" style="white-space: pre-wrap;height: 5rem;">
      <div>
        <b>{{ item.original_name }}</b>
      </div>
      <div>
        {{ item.character }}
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'Cast Item',
  props: {
    item: {
      profile_path: String,
      character: String,
      original_name: String
    }
  }
}
</script>
