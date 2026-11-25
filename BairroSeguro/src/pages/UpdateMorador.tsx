import { useEffect, useState } from "react";
import type { FormEvent } from "react";
import type { MoradorItem } from "./ListMorador";

export interface UpdateMoradorPayload {
  nomeCompleto: string;
  genero: string;
  dataDeNascimento: string;
  telefone: string;
}

interface UpdateMoradorProps {
  morador: MoradorItem | null;
  onVoltar: () => void;
  onAtualizado?: () => void;
}

const formInicial: UpdateMoradorPayload = {
  nomeCompleto: "",
  genero: "",
  dataDeNascimento: "",
  telefone: "",
};

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

export function UpdateMorador({
  morador,
  onAtualizado,
}: UpdateMoradorProps) {
  const [formData, setFormData] = useState<UpdateMoradorPayload>(formInicial);
  const [sending, setSending] = useState<boolean>(false);
  const [erro, setErro] = useState<string | null>(null);
  const [sucess, setSucess] = useState<string | null>(null);

  useEffect(() => {
    if (!morador) {
      setFormData(formInicial);
      return;
    }

    setFormData({
      nomeCompleto: String(morador.nomeCompleto ?? ""),
      genero: String(morador.genero ?? ""),
      dataDeNascimento: String(morador.dataDeNascimento ?? ""),
      telefone: String(morador.telefone ?? ""),
    });
    setErro(null);
    setSucess(null);
  }, [morador]);

  function handleChange(
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();

    if (!morador?.id) {
      setErro("Nenhum morador selecionado para atualizar.");
      return;
    }

    setSending(true);
    setErro(null);
    setSucess(null);

    try {
      const response = await fetch(
        `${URL}/${encodeURIComponent(morador.id)}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (!response.ok) {
        throw new Error(await lerErroHttp(response));
      }

      setSucess("Morador atualizado com sucesso!");
      onAtualizado?.();
    } catch (err) {
      setErro(
        err instanceof Error ? err.message : "Não foi possível atualizar o morador."
      );
    } finally {
      setSending(false);
    }
  }

  if (!morador) {
    return (
      <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
        <h1>Atualizar morador</h1>
        <p>Nenhum morador selecionado.</p>
      </main>
    );
  }

  return (
    <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          gap: "0.75rem",
          marginBottom: "1rem",
        }}
      >
        <h1 style={{ margin: 0 }}>Atualizar morador</h1>
      </div>

      <p style={{ marginBottom: "1rem" }}>
        <small>ID: {morador.id}</small>
      </p>

      <form onSubmit={handleSubmit} style={{ display: "grid", gap: "0.75rem" }}>
        <label>
          Nome completo
          <input
            type="text"
            name="nomeCompleto"
            value={formData.nomeCompleto}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Gênero
          <input
            type="text"
            name="genero"
            value={formData.genero}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Data de nascimento
          <input
            type="date"
            name="dataDeNascimento"
            value={formData.dataDeNascimento}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Telefone
          <input
            type="text"
            name="telefone"
            value={formData.telefone}
            onChange={handleChange}
            required
          />
        </label>

        <button type="submit" disabled={sending}>
          {sending ? "Salvando..." : "Salvar alterações"}
        </button>
      </form>

      {sucess && <p style={{ color: "green", marginTop: "1rem" }}>{sucess}</p>}
      {erro && <p style={{ color: "red", marginTop: "1rem" }}>Erro: {erro}</p>}
    </main>
  );
}