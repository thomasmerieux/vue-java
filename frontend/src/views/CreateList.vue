<template>
  <div class="form-check form-check-inline w-50 pt-5">
    <input v-model="message" placeholder="Name of the list" class="form-control" style="border-radius:0;"/>
    <button type="submit"
            class="btn btn-primary"
            v-on:click="this.createList"
            style="border-radius:0;"
            :disabled="message.length === 0">
      Create
    </button>
  </div>
</template>


<script>
export default {
  name: 'Create List',
  data() {
    return {
      message: ''
    }
  },
  methods: {
    async createList() {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          label: this.message
        })
      };
      await fetch(`http://localhost:8080/list`, requestOptions).then(async (res) => {
        const list = await res.json();
        if (this.$route.query.movieId) {
          const requestOptions = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
              movie_id: this.$route.query.movieId
            })
          };
          await fetch(`http://localhost:8080/list/${list.id}`, requestOptions);
        }
      }).then(() => {
        this.$router.push({
          path: `/list`
        });
      });
    }
  }
}
</script>
