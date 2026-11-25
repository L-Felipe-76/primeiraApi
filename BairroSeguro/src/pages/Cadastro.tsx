import { useState} from "react";
import type { FormEvent} from "react";

export interface RegisterPayload {
  nomeCompleto: string;
  genero: "M" | "F" | string;
  dataDeNascimento: string;
  email: string;
  password: string;
  telefone: string;
  cep: string;
  estado: string;
  cidade: string;
  bairro: string;
  rua: string;
  numeroCasa: string;
  referenciasRua: string;
};

const formInicial: RegisterPayload = {
  nomeCompleto: "",
  genero: "M",
  dataDeNascimento: "",
  email: "",
  password: "",
  telefone: "",
  cep: "",
  estado: "",
  cidade: "",
  bairro: "",
  rua: "",
  numeroCasa: "",
  referenciasRua: ""
};

export function Register() {
  const [formData, setFormData] = useState<RegisterPayload>(formInicial);
  const [sending, setSending] = useState(false);
  const [erro, setErro] = useState<string | null>(null);
  const [sucess, setSucess] = useState<string | null>(null);

  function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setSending(true);
    setErro(null);
    setSucess(null);

    try {
      const response = await fetch("http://localhost:8081/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const contentType = response.headers.get("content-type") || "";
        if (contentType.includes("application/json")) {
          const errorBody = await response.json().catch(() => null);
          throw new Error(
            errorBody?.message || `Erro HTTP ${response.status}`
          );
        }
        const errorText = await response.text().catch(() => "");
        throw new Error(errorText || `Erro HTTP ${response.status}`);
      }

      const resultado = await response.json().catch(() => null);
      setSucess("Cadastro realizado com sucesso!");
      setFormData(formInicial);
      return resultado;
      
    } catch (err) {
        if (err instanceof Error) {
        setErro(err.message);
        } else {setErro("Não foi possível concluir o cadastro.");}

    } finally {
        setSending(false);
    }
  }

  return (
    <main style={{ maxWidth: 520, margin: "2rem auto", padding: "0 1rem" }}>
      <h1>Cadastro</h1>

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
          <select name="genero" value={formData.genero} onChange={handleChange}>
            <option value="M">Masculino (M)</option>
            <option value="F">Feminino (F)</option>
          </select>
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
          E-mail
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Senha
          <input
            type="password"
            name="password"
            value={formData.password}
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
          />
        </label>

        <label>
          CEP
          <input
            type="text"
            name="cep"
            value={formData.cep}
            onChange={handleChange}
          />
        </label>

        <label>
          Estado
          <input
            type="text"
            name="estado"
            value={formData.estado}
            onChange={handleChange}
          />
        </label>

        <label>
          Cidade
          <input
            type="text"
            name="cidade"
            value={formData.cidade}
            onChange={handleChange}
          />
        </label>

        <label>
          Bairro
          <input
            type="text"
            name="bairro"
            value={formData.bairro}
            onChange={handleChange}
          />
        </label>

        <label>
          Rua
          <input
            type="text"
            name="rua"
            value={formData.rua}
            onChange={handleChange}
          />
        </label>

        <label>
          Número da casa
          <input
            type="text"
            name="numeroCasa"
            value={formData.numeroCasa}
            onChange={handleChange}
          />
        </label>

        <label>
          Referências da rua
          <input
            type="text"
            name="referenciasRua"
            value={formData.referenciasRua}
            onChange={handleChange}
          />
        </label>

        <button type="submit" disabled={sending}>
          {sending ? "Enviando..." : "Cadastrar"}
        </button>
      </form>

      {sucess && <p style={{ color: "green", marginTop: "1rem" }}>{sucess}</p>}
      {erro && <p style={{ color: "red", marginTop: "1rem" }}>Erro: {erro}</p>}
    </main>
  );
}