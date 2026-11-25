import { useEffect, useState } from "react";
import type { FormEvent } from "react";

export interface MoradorItem {
  id: string;
  nomeCompleto: string;
  genero: string;
  dataDeNascimento: string;
  telefone: string;
}

interface MoradoresProps {
  onIrParaCriar: () => void;
  onIrParaEditar: (morador: MoradorItem) => void;
}

const URL = "http://localhost:8081/bairroSeguro/moradores";

async function lerErroHttp(response: Response): Promise<string> {
  const contentType = response.headers.get("content-type") || "";
  if (contentType.includes("application/json")) {
    const errorBody = await response.json().catch(() => null);
    return errorBody?.message || errorBody?.error || `Erro HTTP ${response.status}`;
  }
  const errorText = await response.text().catch(() => "");
  return errorText || `Erro HTTP ${response.status}`;
}

export function Moradores({ onIrParaCriar, onIrParaEditar }: MoradoresProps) {
  const [moradores, setMoradores] = useState<MoradorItem[]>([]);
  const [idBusca, setIdBusca] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [buscandoId, setBuscandoId] = useState<boolean>(false);
  const [excluindoId, setExcluindoId] = useState<string | null>(null);
  const [erro, setErro] = useState<string | null>(null);
  const [mensagem, setMensagem] = useState<string | null>(null);

  async function carregarTodosMoradores() {
    setLoading(true);
    setErro(null);

    try {
      const response = await fetch(URL, {
        method: "GET",
        headers: {
          Accept: "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(await lerErroHttp(response));
      }

      const data = await response.json().catch(() => []);
      setMoradores(Array.isArray(data) ? data : []);
    } catch (err) {
      setErro(err instanceof Error ? err.message : "Erro ao listar moradores.");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    carregarTodosMoradores();
  }, []);

  async function handleBuscarPorId(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const idLimpo = idBusca.trim();

    if (!idLimpo) {
      setMensagem(null);
      await carregarTodosMoradores();
      return;
    }

    setBuscandoId(true);
    setErro(null);
    setMensagem(null);

    try {
      const response = await fetch(`${URL}/${encodeURIComponent(idLimpo)}`, {
        method: "GET",
        headers: {
          Accept: "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(await lerErroHttp(response));
      }

      const moradorEncontrado: MoradorItem = await response.json();
      setMoradores(moradorEncontrado ? [moradorEncontrado] : []);
    } catch (err) {
      setMoradores([]);
      setErro(err instanceof Error ? err.message : "Morador não encontrado.");
    } finally {
      setBuscandoId(false);
    }
  }

  async function handleLimparBusca() {
    setIdBusca("");
    setMensagem(null);
    await carregarTodosMoradores();
  }

  async function handleExcluir(id: string) {
    setExcluindoId(id);
    setErro(null);
    setMensagem(null);

    try {
      const response = await fetch(`${URL}/${encodeURIComponent(id)}`, {
        method: "DELETE",
        headers: {
          Accept: "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(await lerErroHttp(response));
      }

      setMoradores((prev) => prev.filter((item) => item.id !== id));
      setMensagem("Morador excluído com sucesso!");
    } catch (err) {
      setErro(err instanceof Error ? err.message : "Erro ao excluir morador.");
    } finally {
      setExcluindoId(null);
    }
  }

  return (
    <main style={{ maxWidth: 640, margin: "2rem auto", padding: "0 1rem" }}>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          gap: "0.75rem",
          marginBottom: "1.25rem",
        }}
      >
        <h1 style={{ margin: 0 }}>Moradores</h1>

        <button type="button" onClick={onIrParaCriar}>
          Adicionar morador
        </button>
      </div>

      <form
        onSubmit={handleBuscarPorId}
        style={{ display: "flex", gap: "0.5rem", marginBottom: "1.25rem" }}
      >
        <input
          type="text"
          placeholder="Buscar por ID (UUID)..."
          value={idBusca}
          onChange={(e) => setIdBusca(e.target.value)}
          style={{ flex: 1 }}
        />
        <button type="submit" disabled={buscandoId || loading}>
          {buscandoId ? "Buscando..." : "Buscar por ID"}
        </button>
        <button
          type="button"
          onClick={handleLimparBusca}
          disabled={buscandoId || loading}
        >
          Todos
        </button>
      </form>

      {mensagem && (
        <p style={{ color: "green", marginBottom: "1rem" }}>{mensagem}</p>
      )}
      {erro && <p style={{ color: "red", marginBottom: "1rem" }}>Erro: {erro}</p>}

      {loading ? (
        <p>Carregando moradores...</p>
      ) : moradores.length === 0 ? (
        <p>Nenhum morador encontrado.</p>
      ) : (
        <ul
          style={{
            listStyle: "none",
            padding: 0,
            margin: 0,
            display: "grid",
            gap: "0.75rem",
          }}
        >
          {moradores.map((morador) => (
            <li
              key={morador.id}
              style={{
                border: "1px solid #ccc",
                borderRadius: 6,
                padding: "0.85rem 1rem",
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                gap: "1rem",
              }}
            >
              <div style={{ display: "grid", gap: "0.2rem" }}>
                <strong>{morador.nomeCompleto || "Sem nome"}</strong>
                <small>ID: {morador.id}</small>
                {morador.genero && <small>Gênero: {morador.genero}</small>}
                {morador.dataDeNascimento && (
                  <small>Nascimento: {morador.dataDeNascimento}</small>
                )}
                {morador.telefone && <small>Telefone: {morador.telefone}</small>}
              </div>

              <button type="button" onClick={() => onIrParaEditar(morador)}>
                Editar
              </button>

              <button
                type="button"
                onClick={() => handleExcluir(morador.id)}
                disabled={excluindoId === morador.id}
              >
                {excluindoId === morador.id ? "Excluindo..." : "Excluir"}
              </button>
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}