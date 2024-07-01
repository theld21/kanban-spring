<template>
<section class="bg-gray-50 dark:bg-gray-900 w-full">
  <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
      <p class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
        THELD
      </p>
      <div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
          <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
              <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                  Register your account
              </h1>
              <div class="space-y-4 md:space-y-6">
                  <div>
                      <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                      <input v-model.trim="email" type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="">
                  </div>
                  <div>
                      <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                      <input v-model.trim="password" type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
                  </div>
                  <BaseButton label="Register" @action="handleRegister" class="bg-savoy mt-6"/>
                  <p class="text-sm font-light text-gray-500 dark:text-gray-400">
                      Have an account? <NuxtLink to="/login" class="font-medium text-primary-600 hover:underline dark:text-primary-500">Sign in</NuxtLink>
                  </p>
                </div>
          </div>
      </div>
  </div>
</section>
</template>
<script setup lang="ts">
import { useKanbanStore } from "~~/stores";

definePageMeta({
  layout: 'auth'
})

//Refs
const email = ref<string>("");
const password = ref<string>("");

//Store
const store = useKanbanStore();
const { register } = store;

const handleRegister = () => {
  register(email.value, password.value);
};
</script>