<template>
  <div class="calculator">
    <h1>Net Income Calculator</h1>

    <form @submit.prevent="handleSubmit">
      <label for="revenue">Revenue:</label>
      <input type="number" id="revenue" v-model.number="formData.revenue" required>
      
      <label for="expenses">Expenses:</label>
      <input type="number" id="expenses" v-model.number="formData.expenses" required>
      
      <button type="submit">Calculate Net Income</button>
    </form>

    <div v-if="netIncome !== null">
      <h2>Net Income:</h2>
      <p>{{ netIncome }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formData: {
        revenue: null,
        expenses: null
      },
      netIncome: null
    };
  },
  methods: {
    async handleSubmit() {
      try {
        const response = await fetch('http://localhost:5000/api/calculate', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.formData)
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        const responseData = await response.json();
        this.netIncome = responseData.net_income;
      } catch (error) {
        console.error('Error calculating net income:', error);
      }
    }
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
