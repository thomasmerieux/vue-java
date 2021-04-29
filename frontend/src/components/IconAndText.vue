<template>
  <div style="cursor: pointer" v-on:click="$emit('clicked')" class="d-flex align-items-center">
    <div>
      <font-awesome-icon :icon="[lib, icon]" size="2x"  class="pr-2"/>
    </div>
    <div>
      {{ label }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'Icon and Text',
  props: {
    lib: {
      type: String,
      required: true
    },
    icon: {
      type: String,
      required: true
    },
    label: String
  }
}
</script>
