<template>
  <transition name="modal-fade">
    <div class="custom-modal-backdrop">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">
                <slot name="header">
                  Default header
                </slot>
              </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="close"></button>

              <button
                  type="button"
                  class="btn-close"
                  @click="close"
              >
                x
              </button>
            </div>

            <div class="modal-body">
              <slot name="body">
                This is the default body
              </slot>
            </div>

            <div class="modal-footer">
              <slot name="footer">
                Default footer
              </slot>
              <button
                  type="button"
                  class="btn btn-secondary"
                  @click="close"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
  </transition>
</template>


<script>
export default {
  name: 'Modal',
  data() {
    return {
      data: null
    }
  },
  props: ['movie'],
  methods: {
    close() {
      this.$emit('close');
    },

    async test() {
      const res = await fetch(`http://localhost:8080/list`);
      const data = await res.json();
      this.data = data;
      return data;
    },
    async addToList(listId) {
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          movie_id: 19
        })
      };
      await fetch(`http://localhost:8080/list/${listId}`, requestOptions);
    }
  },
  beforeMount() {
    this.test()
  },
};
</script>


<style>
.custom-modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}



.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: #4AAE9B;
  background: transparent;
}

.modal-content {
  height: 20rem;
  width: 30rem;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity .5s ease;
}
</style>
