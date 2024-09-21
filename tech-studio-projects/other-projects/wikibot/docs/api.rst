API
===

WikiBot provides a simple API to get responses based on user input.

**Endpoint:** `/chat`

**Method:** `POST`

**Parameters:**

- `user_input` (str): The keyword or phrase to search on Wikipedia.

**Response:**

- `bot_response` (str): The introductory text of the Wikipedia article matching the keyword.

Example request using `curl`:

.. code-block:: bash

   curl -X POST -F 'user_input=Python' http://localhost:5000/chat
