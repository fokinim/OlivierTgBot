FROM openjdk-19
COPY --from=build /app .
