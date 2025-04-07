# Huffman Coding Compressor

A Java-based implementation of the Huffman Coding algorithm for compressing and decompressing text and files. The project includes both a command-line interface for testing and a fully featured GUI for end-users.


## Features

- Encode and decode text using Huffman Coding
- Compress and decompress files
- Calculate character frequencies and probabilities
- Interactive graphical user interface (GUI)
- Modular panel design with tab navigation
- Error and success message handling


## Project Structure
```text
mg/huffman/
├── codec/
│   ├── HuffmanCodec.java
│   ├── HuffmanTree.java
│   └── Node.java
│
├── io/
│   └── HuffmanIO.java
│
└── gui/
    ├── HuffmanGUI.java
    ├── TabsManager.java
    ├── panels/
    │   ├── BasePanel.java
    │   ├── ClearablePanel.java
    │   ├── FileCompressionPanel.java
    │   ├── FileDecompressionPanel.java
    │   ├── FileOperationPanel.java
    │   ├── TextEncodingPanel.java
    │   └── TextDecodingPanel.java
HuffmanCoding.java
```


## Getting Started

### Prerequisites

- Java 8 or higher
- Any IDE (e.g., IntelliJ, Eclipse, VS Code)
- Maven

### Run 

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="mg.huffman.HuffmanCoding"
```


## How Huffman Coding Works

1. Calculates character frequencies from input text
2. Builds a binary tree where least frequent characters have the longest codes
3. Generates binary codes for each character
4. Encodes the input using the generated codes
5. Decodes the binary data using the tree


## License

This project is open-source and free to use under the MIT License.


## Acknowledgments

- Huffman Coding Algorithm (David A. Huffman, 1952)
- Java Swing for GUI development
