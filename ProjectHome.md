Trabalho de desenvolvimento da disciplina de Construção de Compiladores I.

## Equipe ##

Alexandre Sombra Lima - 0336554 <br>
André Luis da Costa Mendonça - 0337068 <br>
Artur Mesquita Barbosa - 0338958 <br>
Ricardo Goes de Meira - 0335325 <br>

<h2>Introdução</h2>

Esse trabalho teve como objetivo colocar em prática os conceitos demonstrados em sala de aula acerca da construção de um compilador. Em especial, trabalhou-se na construção do compilador de MiniJava.<br>
<br>
<h2>Fases de construção</h2>
<ul><li>Análise Léxica e Sintática<br>
Nesta fase inicial, implementamos as associações dos tokens com o código do MiniJava. Além disso, implementamos a análise sintática, isto é, se o código segue a ordem estabelecida pela gramática <a href='1.md'>1</a>. Esta fase não apresentou dificuldades.<br>
Implementado por: Alexandre Sombra e Artur Barbosa.<br>
Realizado: 100%</li></ul>

<ul><li>Árvore Sintática<br>
Nesta fase, ajustamos o código da gramática para gerar a árvore sintática. O analisador sintático também foi alterado de forma a facilitar na construção da árvore (Principalmente as classes de Expressões). Esta fase apresentou pouca dificuldade devido os ajustes ao analisador sintático.<br>
Implementado por: Alexandre Sombra, André Luis e Artur Barbosa.<br>
Realizado: 100%</li></ul>

<ul><li>Tabela de Símbolos<br>
Nesta fase, implementamos uma tabela de símbolos para o Programa, para cada classe e cada método, de forma que não haja conflito entre dois símbolos. Esta fase apresentou dificuldade média, uma vez que houve dificuldades no entendimento da implementação.<br>
Implementado por: Artur Barbosa<br>
Realizado: 100%</li></ul>

<ul><li>Verificação de Tipos<br>
Nesta fase, implementamos um verificador de tipos a partir da tabela de símbolos, armazenando os tipos e verificando se as associações estão completas. Isso concluí a análise semântica. Esta fase apresentou dificuldade média, porém menor que a Tabela de Símbolos, uma vez que a atividade foi parecida.<br>
Implementado por: Artur Barbosa<br>
Realizado: 100%</li></ul>

<ul><li>Código Intermédiario<br>
Nesta fase, implementamos o visitor de associação do código de MiniJava para o código em IR. Esta fase não foi testada. Esta fase apresentou dificuldade alta, devido as discussões da equipe sobre como realizar esta atividade e dificuldade no entendimento da implementação.<br>
Implementado por: Todos os membros da equipe.<br>
Realizado: 70%</li></ul>

<ul><li>Seleção de Instruções<br>
Foram implementadas apenas algumas funcionalidades necessárias para a analise de longevidade e seleção de registradores.<br>
Realizado: 40%</li></ul>

<ul><li>Análise de Longevidade<br>
Implementada por completo, mas não foi testada, por necessidade do modulo anterior do compilador. Foi implementada toda a construção do gráfico de fluxo e o cálculo da longevidade das variáveis.<br>
Implementado por: Alexandre Sombra<br>
Realizado: 90%</li></ul>

<ul><li>Alocação de Registradores<br>
Implementada por completo como proposto pelo livro, que diz para não implementar coalesce e spilling para facilitar. Também não foi testada por necessidade dos módulos anteriores do compilador.<br>
Implementado por: Alexandre Sombra<br>
Realizado: 90%</li></ul>


<h2>Bibliografia</h2>
<a href='1.md'>1</a> Andrew W. Appel, Jean Palsberg: Modern Compiler Implementation in Java, Second Edition. Cambridge University Press, 2002 (501 pages).