import express from "express";
import bodyParser from "body-parser";
import logRoutes from "./routes/logRoutes";

const app = express();
app.use(bodyParser.json());

app.use("/api", logRoutes);

export default app;
