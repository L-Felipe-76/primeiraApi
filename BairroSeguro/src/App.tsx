import { useState } from "react";
import type { FormEvent } from "react";
import "./App.css";
import { Register } from "./pages/Cadastro";
import { Home } from "./pages/Home";
import { Moradores } from "./pages/ListMorador";
import type { MoradorItem } from "./pages/ListMorador";
import { CreateMoradorFunc } from "./pages/CreateMorador";
import { UpdateMorador } from "./pages/UpdateMorador"
import { CreateOcorrenciaFunc } from "./pages/CreateOcorrencia";
import { CreateGrupoDeApoioFunc } from "./pages/CreateGrupoDeApoio";
import { CreateContatoDeEmergenciaFunc } from "./pages/CreateContatoEmergencia";

type TelaAtiva = 
"login" | 
"cadastro" | 
"home" | 
"morador" | 
"criarMorador" |
"updateMorador" |
"ocorrencia" | 
"redeDeApoio" | 
"contatoDeEmergencia";

interface LoginPayload {
  email: string;
  password: string;
}

const loginInicio: LoginPayload = {
  email: "",
  password: "",
};

export function App() {
  const [tela, setTela] = useState<TelaAtiva>("login");
  const [loginData, setLoginData] = useState<LoginPayload>(loginInicio);
  const [sending, setSending] = useState<boolean>(false);
  const [erro, setErro] = useState<string | null>(null);
  const [sucess, setSucess] = useState<string | null>(null);

  const [moradorSelecionado, setMoradorSelecionado] =
    useState<MoradorItem | null>(null);

  const estaAutenticado = 
    tela === "home" || 
    tela === "morador" || 
    tela === "criarMorador" ||
    tela === "updateMorador" ||
    tela === "ocorrencia" || 
    tela === "redeDeApoio" || 
    tela === "contatoDeEmergencia";

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  function handleLogout() {
    setSucess(null);
    setErro(null);
    setLoginData(loginInicio);
    setTela("login");
    setMoradorSelecionado(null);
  }

  async function handleLoginSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setSending(true);
    setErro(null);
    setSucess(null);

    try {
      const response = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(loginData),
      });

      if (!response.ok) {
        const contentType = response.headers.get("content-type") || "";
        if (contentType.includes("application/json")) {
          const errorBody = await response.json().catch(() => null);
          throw new Error(errorBody?.message || `Erro HTTP ${response.status}`);
        }
        const errorText = await response.text().catch(() => "");
        throw new Error(errorText || `Erro HTTP ${response.status}`);
      }

      setSucess("Login realizado com sucesso!");
      setTela("home");
    } catch (err) {
      if (err instanceof Error) {
        setErro(err.message);
      } else {
        setErro("Não foi possível realizar o login.");
      }
    } finally {
      setSending(false);
    }
  }

  return (
    <div style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <nav style={{ display: "flex", gap: "0.5rem", marginBottom: "1.5rem" }}>
        {!estaAutenticado ? (
          <>
            <button
              type="button"
              onClick={() => {
                setErro(null);
                setSucess(null);
                setTela("login");
              }}
              disabled={tela === "login"}
            >
              Login
            </button>

            <button
              type="button"
              onClick={() => {
                setErro(null);
                setSucess(null);
                setTela("cadastro");
              }}
              disabled={tela === "cadastro"}
            >
              Cadastro
            </button>
          </>
        ) : (
          <>
            {(tela === "morador" ||
              tela === "criarMorador" ||
              tela === "updateMorador" ||
              tela === "ocorrencia" || 
              tela === "redeDeApoio" || 
              tela === "contatoDeEmergencia") && (
              <button type="button" onClick={() => setTela("home")}>
                Home
              </button>
            )}
            {(tela === "criarMorador" || tela === "updateMorador") && (
              <button type="button" onClick={() => setTela("morador")}>
                Voltar para moradores
              </button>
            )}

            <button type="button" onClick={handleLogout}>
              Sair
            </button>
          </>
        )}
      </nav>

      {tela === "cadastro" && <Register />}

      {tela === "home" && (
        <section>
          {sucess && (
            <p style={{ color: "green", marginBottom: "1rem" }}>{sucess}</p>
          )}
          <Home onNavigate={(rota) => setTela(rota)} />
        </section>
      )}

      {tela === "morador" && (
        <Moradores 
          onIrParaCriar={() => setTela("criarMorador")}
          onIrParaEditar={(morador) => {
            setMoradorSelecionado(morador);
            setTela("updateMorador"); }}
        />
      )}
      {tela === "criarMorador" && <CreateMoradorFunc />}
      {tela === "updateMorador" && (
        <UpdateMorador
          morador={moradorSelecionado}
          onVoltar={() => setTela("morador")}
        /> )}

      {tela === "ocorrencia" && <CreateOcorrenciaFunc />}

      {tela === "redeDeApoio" && <CreateGrupoDeApoioFunc />}

      {tela === "contatoDeEmergencia" && <CreateContatoDeEmergenciaFunc />}

      {tela === "login" && (
        <main>
          <h1>Login</h1>

          <form
            onSubmit={handleLoginSubmit}
            style={{ display: "grid", gap: "0.75rem" }}
          >
            <label>
              E-mail
              <input
                type="email"
                name="email"
                value={loginData.email}
                onChange={handleChange}
                required
              />
            </label>

            <label>
              Senha
              <input
                type="password"
                name="password"
                value={loginData.password}
                onChange={handleChange}
                required
              />
            </label>

            <button type="submit" disabled={sending}>
              {sending ? "Entrando..." : "Entrar"}
            </button>
          </form>

          {erro && (
            <p style={{ color: "red", marginTop: "1rem" }}>Erro: {erro}</p>
          )}
        </main>
      )}
    </div>
  );
}

export default App;