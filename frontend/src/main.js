import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import Toaster from '@meforma/vue-toaster';
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserSecret, faPhone, faBookmark as solidFaBookmark, faHeart as solidFaHeart, faList, faPlusCircle} from '@fortawesome/free-solid-svg-icons'
import { faBookmark, faHeart, faTimesCircle } from '@fortawesome/free-regular-svg-icons';

library.add(faUserSecret, faPhone, faBookmark, faHeart, solidFaBookmark, solidFaHeart, faTimesCircle, faList, faPlusCircle);


createApp(App)
    .component('font-awesome-icon', FontAwesomeIcon)
    .use(Toaster)
    .use(router).mount('#app');
