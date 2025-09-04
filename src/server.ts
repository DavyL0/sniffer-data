import app from "./app";
import { connectDB } from "./db/database";
import { startWatching } from "./watchers/logWatcher";

const PORT = 3000;

async function start() {
  await connectDB();

  app.listen(PORT, () => {
    console.log(`🚀 API rodando em http://localhost:${PORT}`);
  });

  // Inicia monitoramento de logs
  startWatching("./logs_externos.txt");
}

start();
