import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        ListView(phrases: viewModel.items)
            .onAppear {
                self.viewModel.startObserving()
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var items: Array<QiitaItem> = []

        func startObserving() {
            Task {
                for await items in QiitaComponent().getItems() {
                    self.items = items
                }
            }
        }
    }
}

struct ListView: View {
    let phrases: Array<QiitaItem>

    var body: some View {
        List(phrases, id: \.self) {
            Text($0.title)
        }
    }
}
