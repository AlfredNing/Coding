import chromadb

client = chromadb.PersistentClient(path='./collection.pkl')
collection = client.create_collection(name='demo')