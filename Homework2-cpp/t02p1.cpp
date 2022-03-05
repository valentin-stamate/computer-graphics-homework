
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

#include "glut.h"

// dimensiunea ferestrei in pixeli
#define dim 300

unsigned char prevKey;

// concoida lui Nicomede (concoida dreptei)
// $x = a + b \cdot cos(t), y = a \cdot tg(t) + b \cdot sin(t)$. sau
// $x = a - b \cdot cos(t), y = a \cdot tg(t) - b \cdot sin(t)$. unde
// $t \in (-\pi / 2, \pi / 2)$
void Display1() {
	double xmax, ymax, xmin, ymin;
	double a = 1, b = 2;
	double pi = 4 * atan(1.0);
	double ratia = 0.05;
	double t;

	// calculul valorilor maxime/minime ptr. x si y
	// aceste valori vor fi folosite ulterior la scalare
	xmax = a - b - 1;
	xmin = a + b + 1;
	ymax = ymin = 0;
	for (t = -pi / 2 + ratia; t < pi / 2; t += ratia) {
		double x1, y1, x2, y2;
		x1 = a + b * cos(t);
		xmax = (xmax < x1) ? x1 : xmax;
		xmin = (xmin > x1) ? x1 : xmin;

		x2 = a - b * cos(t);
		xmax = (xmax < x2) ? x2 : xmax;
		xmin = (xmin > x2) ? x2 : xmin;

		y1 = a * tan(t) + b * sin(t);
		ymax = (ymax < y1) ? y1 : ymax;
		ymin = (ymin > y1) ? y1 : ymin;

		y2 = a * tan(t) - b * sin(t);
		ymax = (ymax < y2) ? y2 : ymax;
		ymin = (ymin > y2) ? y2 : ymin;
	}

	xmax = (fabs(xmax) > fabs(xmin)) ? fabs(xmax) : fabs(xmin);
	ymax = (fabs(ymax) > fabs(ymin)) ? fabs(ymax) : fabs(ymin);

	// afisarea punctelor propriu-zise precedata de scalare
	glColor3f(1, 0.1, 0.1); // rosu
	glBegin(GL_LINE_STRIP);
	for (t = -pi / 2 + ratia; t < pi / 2; t += ratia) {
		double x1, y1, x2, y2;
		x1 = (a + b * cos(t)) / xmax;
		x2 = (a - b * cos(t)) / xmax;
		y1 = (a * tan(t) + b * sin(t)) / ymax;
		y2 = (a * tan(t) - b * sin(t)) / ymax;

		glVertex2f(x1, y1);
	}
	glEnd();

	glBegin(GL_LINE_STRIP);
	for (t = -pi / 2 + ratia; t < pi / 2; t += ratia) {
		double x1, y1, x2, y2;
		x1 = (a + b * cos(t)) / xmax;
		x2 = (a - b * cos(t)) / xmax;
		y1 = (a * tan(t) + b * sin(t)) / ymax;
		y2 = (a * tan(t) - b * sin(t)) / ymax;

		glVertex2f(x2, y2);
	}
	glEnd();
}

// graficul functiei 
// $f(x) = \bar sin(x) \bar \cdot e^{-sin(x)}, x \in \langle 0, 8 \cdot \pi \rangle$, 
void Display2() {
	double pi = 4 * atan(1.0);
	double xmax = 8 * pi;
	double ymax = exp(1.1);
	double ratia = 0.05;

	// afisarea punctelor propriu-zise precedata de scalare
	glColor3f(1, 0.1, 0.1); // rosu
	glBegin(GL_LINE_STRIP);
	for (double x = 0; x < xmax; x += ratia) {
		double x1, y1;
		x1 = x / xmax;
		y1 = (fabs(sin(x)) * exp(-sin(x))) / ymax;

		glVertex2f(x1, y1);
	}
	glEnd();
}
double f_2_1(double x) {
	if (x == 0)
		return 1;
	return fabs(x - floor(x + 0.5)) / x;
}
void Display3() {
	float xmax = 100;
	float ymax = 1;
	float ratia = 0.25;
	glColor3f(1, 0.1, 0.1); // rosu
	glBegin(GL_LINE_STRIP);
	for (double x = 0; x < xmax; x += ratia) {
		double x1, y1;
		x1 = x / xmax;
		y1 = f_2_1(x) / ymax;

		glVertex2f(x1, y1);
	}
	glEnd();
}
void Display4() {
	double a = 0.3;
	double b = 0.2;
	double tmax = 4 * atan(1.0);
	double xmax = 1;
	double ymax = 0;
	double ratia = 0.001;
	glColor3f(1, 0.1, 0.1); // rosu
	for (double t = -tmax; t < tmax; t += ratia) {
		double y = 2 * (a * cos(t) + b) * sin(t);
		ymax = fmax(ymax, fabs(y));
	}
	glBegin(GL_LINE_STRIP);
	for (double t = -tmax; t < tmax; t += ratia) {
		double x, y;
		x = 2 * (a * cos(t) + b) * cos(t) / xmax;
		y = 2 * (a * cos(t) + b) * sin(t) / ymax;
		glVertex2f(x, y);

	}
	glEnd();

}
void Display5() {
	double a = 0.2;
	double tmax = 4 * atan(1.0) / 2;
	double xmax = INT_MIN;
	double ymax = INT_MIN;
	double ratia = 0.001;;
	glColor3f(1, 0.1, 0.1); // rosu
	for (double t = -tmax; t < tmax; t += ratia) {
		if (t == tmax / 3 || t == -tmax / 3)
			continue;
		double x = a / (4 * cos(t) * cos(t) - 3);
		double y = a * tan(t) / (4 * cos(t) * cos(t) - 3);
		xmax = fmax(xmax, fabs(x));
		ymax = fmax(ymax, fabs(y));
	}
	glBegin(GL_LINE_STRIP);
	for (double t = -tmax; t < tmax; t += ratia) {
		double x = a / (4 * cos(t) * cos(t) - 3) / xmax;
		double y = a * tan(t) / (4 * cos(t) * cos(t) - 3) / ymax;
		glVertex2f(x, y);

	}
	glEnd();
}
void Display6() {
	double a = 0.1;
	double b = 0.2;
	double tmax = 10;
	double xmax = INT_MIN;
	double ymax = INT_MIN;
	double ratia = 0.0001;
	glColor3f(1, 0.1, 0.1); // rosu
	for (double t = 0; t < tmax; t += ratia) {
		double x = a * t - b * sin(t);
		double y = a - b * cos(t);
		xmax = fmax(xmax, fabs(x));
		ymax = fmax(ymax, fabs(y));
	}

	glBegin(GL_LINE_STRIP);
	for (double t = -tmax; t < tmax; t += ratia) {
		double x = (a * t - b * sin(t)) / xmax;
		double y = (a * t - b * cos(t)) / ymax;
		glVertex2f(t, x);
		glVertex2f(t, y);
	}
	glEnd();

}
void Display7() {}
void Display8() {}
void Display9() {}
void Display10() {}


void Init(void) {

	glClearColor(1.0, 1.0, 1.0, 1.0);

	glLineWidth(1);

	//   glPointSize(4);

	glPolygonMode(GL_FRONT, GL_LINE);
}

void Display(void) {
	glClear(GL_COLOR_BUFFER_BIT);

	switch (prevKey) {
	case '1':
		Display1();
		break;
	case '2':
		Display2();
		break;
	case '3':
		Display3();
		break;
	case '4':
		Display4();
		break;
	case '5':
		Display5();
		break;
	case '6':
		Display6();
		break;
	case '7':
		Display7();
		break;
	case '8':
		Display8();
		break;
	case '9':
		Display9();
		break;
	case '0':
		Display10();
		break;
	default:
		break;
	}

	glFlush();
}

void Reshape(int w, int h) {
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);
}

void KeyboardFunc(unsigned char key, int x, int y) {
	prevKey = key;
	if (key == 27) // escape
		exit(0);
	glutPostRedisplay();
}

void MouseFunc(int button, int state, int x, int y) {
}

int main(int argc, char** argv) {

	glutInit(&argc, argv);

	glutInitWindowSize(dim, dim);

	glutInitWindowPosition(100, 100);

	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);

	glutCreateWindow(argv[0]);

	Init();

	glutReshapeFunc(Reshape);

	glutKeyboardFunc(KeyboardFunc);

	glutMouseFunc(MouseFunc);

	glutDisplayFunc(Display);

	glutMainLoop();

	return 0;
}
