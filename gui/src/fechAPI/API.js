const apiUrl = 'http://localhost:8080/api/exchangeRateMart';

const fetchDataFromApi = async (requestData) => {
  try {
    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requestData)
    });

    if (!response.ok) {
      throw new Error(`Network response was not ok, status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error during fetch operation:', error);
    throw error;
  }
};

export default fetchDataFromApi;
  