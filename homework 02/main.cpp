#pragma clang diagnostic push
#pragma ide diagnostic ignored "cert-flp30-c"
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"

#include <cstdlib>
#include <cmath>

#include <glut.h>

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
        double x1, y1;
        x1 = (a + b * cos(t)) / xmax;
        y1 = (a * tan(t) + b * sin(t)) / ymax;

        glVertex2f(x1, y1);
    }
    glEnd();

    glBegin(GL_LINE_STRIP);
    for (t = -pi / 2 + ratia; t < pi / 2; t += ratia) {
        double x2, y2;
        x2 = (a - b * cos(t)) / xmax;
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
        x = 2 * (a * cos(t) + b) * cos(t) / (xmax * 1.05);
        y = 2 * (a * cos(t) + b) * sin(t) / (ymax * 1.05);
        glVertex2f(x, y);

    }
    glEnd();

}

void Display5() {
    double pi = 4 * atan(1.0);
    double ratia = 0.005;

    double xStart = -0.99;
    double yStart = 0.99;

    double a = 0.2;
    int count = 0;

    glColor3f(1, 0.1, 0.1); // rosu
    glBegin(GL_TRIANGLES);
    glVertex2f(xStart, yStart);

    for (double t = -pi / 2 + ratia; t < -pi / 6; t += ratia, count++) {

        double x = a / (4 * cos(t) * cos(t) - 3);
        double y = (a * tan(t)) / (4 * cos(t) * cos(t) - 3);

        double xNext = a / (4 * cos(t + ratia) * cos(t + ratia) - 3);
        double yNext = (a * tan(t + ratia)) / (4 * cos(t + ratia) * cos(t + ratia) - 3);

        if (x >= xStart && y <= yStart && y > 0.2) {
            if (count % 4 == 2) {
                glVertex2f(x, y);
                glVertex2f(xNext, yNext);
                glVertex2f(xStart, yStart);
            }

        }

    }

    glEnd();

    glColor3f(0.1, 0.1, 0.1);
    glBegin(GL_LINE_LOOP);

    glVertex2f(xStart, yStart);
    glVertex2f(a / (4 * cos(-pi / 2) * cos(-pi / 2) - 3), yStart);

    for (double t = -pi / 2; t < -pi / 6; t += ratia) {

        double x = a / (4 * cos(t) * cos(t) - 3);
        double y = (a * tan(t)) / (4 * cos(t) * cos(t) - 3);

        if (x >= xStart && y <= yStart) {
            glVertex2f(x, y);
        }

    }
    glEnd();


}

void Display6() {
    double a = 0.1;
    double b = 0.2;
    double tmax = 10;
    double xmax = INT_MIN;
    double ymax = INT_MIN;
    double ratia = 0.001;
    glColor3f(1, 0.1, 0.1); // rosu
    for (double t = 0; t < tmax; t += ratia) {
        double x = a * t - b * sin(t);
        double y = a - b * cos(t);
        xmax = fmax(xmax, fabs(x));
        ymax = fmax(ymax, fabs(y));
    }

    glBegin(GL_LINE_STRIP);
    for (double t = -tmax + 0.05; t <= tmax - 0.05; t += ratia) {
        double x = (a * t - b * sin(t)) / (xmax);
        double y = (a - b * cos(t)) / (2 * ymax);
        glVertex2f(x, y);
    }
    glEnd();

}

// Epicicloida
void Display7() {
    double r = 0.3;
    double R = 0.1;

    double pi = 4 * atan(1.0);
    double tmax = 2 * pi;

    double xmax = INT_MIN;
    double ymax = INT_MIN;

    double ratia = 0.001;

    glColor3f(1, 0.1, 0.1); // rosu

    for (double t = 0; t < tmax; t += ratia) {
        double x = (R + r) * cos(r * t / R) - r * cos(t + r * t / R);
        double y = (R + r) * sin(r * t / R) - r * sin(t + r * t / R);

        xmax = fmax(xmax, fabs(x));
        ymax = fmax(ymax, fabs(y));
    }

    glBegin(GL_LINE_STRIP);
    for (double t = 0; t < tmax; t += ratia) {
        double x = ((R + r) * cos(r * t / R) - r * cos(t + r * t / R)) / (xmax * 1.5);
        double y = ((R + r) * sin(r * t / R) - r * sin(t + r * t / R)) / (ymax * 1.5);

        glVertex2f(x, y);
    }
    glEnd();
}

void Display8() {
    double r = 0.3;
    double R = 0.1;

    double pi = 4 * atan(1.0);
    double tmax = 2 * pi;

    double xmax = INT_MIN;
    double ymax = INT_MIN;

    double ratia = 0.001;

    glColor3f(1, 0.1, 0.1); // rosu

    for (double t = 0; t < tmax; t += ratia) {
        double x = (R - r) * cos(r * t / R) - r * cos(t - r * t / R);
        double y = (R - r) * sin(r * t / R) - r * sin(t - r * t / R);

        xmax = fmax(xmax, fabs(x));
        ymax = fmax(ymax, fabs(y));
    }

    glBegin(GL_LINE_STRIP);
    for (double t = 0; t < tmax; t += ratia) {
        double x = ((R - r) * cos(r * t / R) - r * cos(t - r * t / R)) / (xmax * 1.5);
        double y = ((R - r) * sin(r * t / R) - r * sin(t - r * t / R)) / (ymax * 1.5);

        glVertex2f(x, y);
    }
    glEnd();
}

void Display9() {
    double pi = 4 * atan(1.0);
    double tmax = pi / 4;

    double xmax = INT_MIN;
    double ymax = INT_MIN;
    double xmaxN = INT_MIN;
    double ymaxN = INT_MIN;

    double ratia = 0.00001;
    double a = 0.4;

    for (double t = -tmax + ratia; t < tmax; t += ratia) {
        double r = a * sqrt(2 * cos(2 * t));
        double rN = -r;

        double x = r * cos(t);
        double y = r * sin(t);

        double xN = rN * cos(t);
        double yN = rN * sin(t);

        xmax = fmax(xmax, fabs(x));
        ymax = fmax(ymax, fabs(y));

        xmaxN = fmax(xmaxN, fabs(xN));
        ymaxN = fmax(ymaxN, fabs(yN));
    }

    glColor3f(1, 0.1, 0.1); // rosu
    glBegin(GL_LINE_STRIP);

    for (double t = -tmax + ratia; t < tmax; t += ratia) {
        double r = a * sqrt(2 * cos(2 * t));

        double x = r * cos(t) / (xmax * 2);
        double y = r * sin(t) / (ymax * 2);

        glVertex2f(x, y);
    }

    for (double t = -tmax + ratia; t < tmax; t += ratia) {
        double r = a * sqrt(2 * cos(2 * t));
        double rN = -r;

        double xN = rN * cos(t) / (xmaxN * 2);
        double yN = rN * sin(t) / (ymaxN * 2);

        glVertex2f(xN, yN);
    }
    glEnd();
}

void Display10() {
    double tmax = 9.45;

    double xmax = INT_MIN;
    double ymax = INT_MIN;

    double ratia = 0.01;
    double a = 0.02;

    for (double t = ratia; t < tmax; t += ratia) {
        double r = a * exp(1 + t);

        double x = r * cos(t);
        double y = r * sin(t);

        xmax = fmax(xmax, fabs(x));
        ymax = fmax(ymax, fabs(y));
    }

    glColor3f(1, 0.1, 0.1); // rosu
    glBegin(GL_LINE_STRIP);

    for (double t = ratia; t < tmax; t += ratia) {
        double r = a * exp(1 + t);

        double x = r * cos(t) / xmax;
        double y = r * sin(t) / ymax;

        glVertex2f(x, y);
    }

    glEnd();
}


void Init() {

    glClearColor(1.0, 1.0, 1.0, 1.0);

    glLineWidth(1);

    //   glPointSize(4);

    glPolygonMode(GL_FRONT, GL_LINE);
}

void Display() {
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
    glViewport(0, 0, (GLsizei) w, (GLsizei) h);
}

void KeyboardFunc(unsigned char key, int x, int y) {
    prevKey = key;
    if (key == 27) // escape
        exit(0);
    glutPostRedisplay();
}

void MouseFunc(int button, int state, int x, int y) {
}

int main(int argc, char **argv) {

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

#pragma clang diagnostic pop