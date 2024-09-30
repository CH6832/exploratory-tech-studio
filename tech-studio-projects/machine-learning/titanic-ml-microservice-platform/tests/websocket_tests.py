import asyncio
import websockets
import json

async def echo(websocket, path):
    async for message in websocket:
        data = json.loads(message)
        if data.get("action") == "greet":
            response = {"action": "greet", "data": "Hello, client!"}
            await websocket.send(json.dumps(response))

start_server = websockets.serve(echo, "localhost", 8000)

if __name__ == "__main__":
    asyncio.get_event_loop().run_until_complete(start_server)
    print("WebSocket server started on ws://localhost:8000")
    asyncio.get_event_loop().run_forever()
