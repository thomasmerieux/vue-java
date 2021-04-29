<template>
  <div style="cursor: pointer" v-on:click="$emit('clicked')" class="d-flex align-items-center">
    <div>
      <font-awesome-icon :icon="['far', 'times-circle']" size="2x"  class="pr-2"/>
    </div>
    <div>
      {{ label }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'Delete',
  props: {
    label: String
  }
}
</script>
