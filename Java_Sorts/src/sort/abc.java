double error = 0;
for (int i = 0; i < 10; i++){
    error += (output[i] - expected[i])**2;
}

error = error/20;

