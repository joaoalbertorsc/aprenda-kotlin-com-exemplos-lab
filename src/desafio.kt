enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val id: Int, val nome: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        for (usuario in usuarios) {
            if (inscritos.contains(usuario)) {
                println("Usuário ${usuario.nome} já está matriculado na formação '${nome}'.")
            } else {
                inscritos.add(usuario)
                println("Usuário ${usuario.nome} matriculado com sucesso na formação '${nome}'!")
            }
        }
    }

    fun exibirDetalhes() {
        println("----------------------------------------------------")
        println("Formação: ${nome} (Nível: ${nivel})")
        println("Conteúdos:")
        conteudos.forEach {
            println("- ${it.nome} (Duração: ${it.duracao} minutos)")
        }
        println("Inscritos: (${inscritos.size})")
        inscritos.forEach {
            println("- ${it.nome}")
        }
        println("----------------------------------------------------")
    }
}

fun main() {
    // --- Usuários ---
    val jobert = Usuario(1, "Jobert")
    val alice = Usuario(2, "Alice")
    val bob = Usuario(3, "Bob")
    val carol = Usuario(4, "Carol")

    // --- Formação Nível BÁSICO ---
    val conteudosBasicos = listOf(ConteudoEducacional("Introdução à Lógica de Programação", 90))
    val formacaoBasica = Formacao("Primeiros Passos na Programação", conteudosBasicos, Nivel.BASICO)
    formacaoBasica.matricular(jobert, alice)

    // --- Formação Nível INTERMEDIÁRIO ---
    val conteudosIntermediarios = listOf(
        ConteudoEducacional("Kotlin Básico", 120),
        ConteudoEducacional("Programação Orientada a Objetos", 180)
    )
    val formacaoIntermediaria = Formacao("Desenvolvedor Kotlin", conteudosIntermediarios, Nivel.INTERMEDIARIO)
    formacaoIntermediaria.matricular(bob, jobert)

    // --- Formação Nível DIFÍCIL ---
    val conteudosDificeis = listOf(
        ConteudoEducacional("Coroutines em Kotlin", 150),
        ConteudoEducacional("Arquitetura de Microsserviços", 240)
    )
    val formacaoDificil = Formacao("Backend Avançado com Kotlin", conteudosDificeis, Nivel.DIFICIL)
    formacaoDificil.matricular(carol)

    // --- Exibindo os detalhes de cada formação ---
    println("\nExibindo detalhes das formações:\n")
    formacaoBasica.exibirDetalhes()
    formacaoIntermediaria.exibirDetalhes()
    formacaoDificil.exibirDetalhes()

    // --- Testando matrícula duplicada ---
    println("\nTentando matricular Jobert novamente na formação básica...")
    formacaoBasica.matricular(jobert)
}
