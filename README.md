

This project is a Java implementation of a 3D wireframe renderer. It constructs, rotate and projects 3D models onto a 2D screen using linear algebra. The project is heavily inspired by Tsoding's tutorial "One Formula That Demystifies 3D Graphics", which was originally implemented in JavaScript, but this project translates the concepts to Java Swing.

## Features
- 3D Wireframe Rendering. Visualize simple 3D models as wireframes.
- Rotation and scaling.


## Screenshots

## Usage
#### 1. Clone the repository
```
git clone https://github.com/nikkcom/3d-wireframe-render.git
cd 3d-wireframe-render
```

#### 2. Compile and run
```
javac -d out src/main/java/me/niko/*.java src/main/java/me/niko/models/*.java src/main/java/me/niko/view/*.java
java -cp out me.niko.Main
```

#### 3. A window will appear, displaying the model.
